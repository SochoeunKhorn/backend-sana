package com.sochoeun.controller;

import com.sochoeun.entity.Content;
import com.sochoeun.pagination.PageResponse;
import com.sochoeun.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Content content){
        return ResponseEntity.ok(contentService.create(content));
    }

    @GetMapping
    public ResponseEntity<?> getContents(){
        return ResponseEntity.ok(contentService.getContents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getContent(@PathVariable("id")Integer id){
        return ResponseEntity.ok(contentService.getContent(id));
    }
    @GetMapping("/{id}/article")
    public ResponseEntity<?> getPageable(@PathVariable("id")Integer id,
                                         @RequestParam(value = "pageNo",required = false,defaultValue = "0") Integer pageNo,
                                         @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize){
        Page<Content> pageable = contentService.getPageable(id, pageNo, pageSize);
        PageResponse pageResponse = new PageResponse(pageable);
        return ResponseEntity.ok(pageResponse);
    }
    @GetMapping("/{id}/images")
    public ResponseEntity<?> getAllImagesContent(@PathVariable("id")Integer id){
        return ResponseEntity.ok(contentService.getAllImagesContent(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Integer id,@RequestBody Content content){
        Content update = contentService.update(id, content);
        return ResponseEntity.ok(update);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Integer id){
        contentService.delete(id);
        return ResponseEntity.ok("Content ID = %d is deleted".formatted(id));
    }

}
