package com.memo.toko.media.service;

import com.memo.toko.media.model.Media;
import com.memo.toko.media.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    private final MediaRepository repository;

    public MediaService(MediaRepository repository) {
        this.repository = repository;
    }

    public List<Media> findAll() {
        return repository.findAll();
    }

    public Optional<Media> findById(Long id) {
        return repository.findById(id);
    }

    public Media save(Media media) {
        return repository.save(media);
    }

}
