package com.sochoeun.controller;

import com.sochoeun.constants.Constants;
import com.sochoeun.entity.ImportImage;
import com.sochoeun.service.ImportImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.sochoeun.constants.Constants.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("importImage")
public class ImportImageController {
    private final ImportImageService importImageService;
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ImportImage req){
        importImageService.create(req);
        return new ResponseEntity<>("Create successfully", HttpStatus.OK);
    }
    @PutMapping("/photo")
    public ResponseEntity<?> uploadPhoto(@RequestParam("id") String id, @RequestParam("file") MultipartFile file){
        String s = importImageService.uploadPhoto(id, file);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    /*@GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String id){
        return ResponseEntity.ok(importImageService.getById(id));
    }
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(importImageService.getAllImportImage());
    }
    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}
