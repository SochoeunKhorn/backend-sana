package com.sochoeun.controller;

import com.sochoeun.entity.Slide;
import com.sochoeun.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.sochoeun.constants.Constants.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("slides")
public class SlideController {
    private final SlideService slideService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Slide slide){
        slideService.create(slide);
        return ResponseEntity.ok("Created");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSlide(@PathVariable("id") Integer id){
        return ResponseEntity.ok(slideService.getSlide(id));
    }
    @GetMapping()
    public ResponseEntity<?> getSlides(){
        return ResponseEntity.ok(slideService.getSlides());
    }
    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}
