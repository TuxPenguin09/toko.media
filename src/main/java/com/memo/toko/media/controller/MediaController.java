package com.memo.toko.media.controller;

import com.memo.toko.media.model.Media;
import com.memo.toko.media.service.MediaService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService service;

    public MediaController(MediaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Media> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Media> create(@Validated @RequestBody Media media) {
        Media saved = service.save(media);
        return ResponseEntity.created(URI.create("/api/media/" + saved.getId())).body(saved);
    }

    @PostMapping(path = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file provided");
        }

        try {
            // ensure uploads directory exists
            Path uploadsDir = Paths.get("uploads");
            if (!Files.exists(uploadsDir)) {
                Files.createDirectories(uploadsDir);
            }

            String original = Path.of(file.getOriginalFilename()).getFileName().toString();
            String filename = System.currentTimeMillis() + "_" + original;
            Path dest = uploadsDir.resolve(filename);
            Files.write(dest, file.getBytes());

            Media m = Media.builder()
                    .filename(original)
                    .url("/" + dest.toString().replace("\\\\", "/"))
                    .type(file.getContentType())
                    .build();

            Media saved = service.save(m);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save file: " + e.getMessage());
        }
    }

}
