package com.sochoeun.controller;

import com.sochoeun.entity.Image;
import com.sochoeun.entity.Media;
import com.sochoeun.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class MediaController {
    private final ContentService contentService;
    @PostMapping("/images")
    public ResponseEntity<?> uploadImage(@RequestBody Image image){
        contentService.uploadImage(image);
        return ResponseEntity.ok("Upload Images Successfully");
    }

    @PostMapping("/medias")
    public ResponseEntity<?> uploadMedia(@RequestBody Media media){
        contentService.uploadMedia(media);
        return ResponseEntity.ok("Upload Media Successfully");
    }
}
