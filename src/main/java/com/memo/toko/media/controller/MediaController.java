package com.memo.toko.media.controller;

import com.memo.toko.media.model.Media;
import com.memo.toko.media.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

}
