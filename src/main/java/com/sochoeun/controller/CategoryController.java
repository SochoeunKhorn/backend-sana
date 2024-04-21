package com.sochoeun.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categories")
@Tag(name = "Categories")
// Name on OpenApi UI
public class CategoryController {

    @GetMapping()
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok("Get Categories");
    }
}
