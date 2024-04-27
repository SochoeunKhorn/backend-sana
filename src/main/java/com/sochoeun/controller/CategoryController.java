package com.sochoeun.controller;

import com.sochoeun.entity.Category;
import com.sochoeun.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
@Tag(name = "Categories")
// Name on OpenApi UI
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping()
    public ResponseEntity<?> getCategories(){
        List<Category> allCategory = categoryService.getAllCategory();
        return ResponseEntity.ok(allCategory);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Integer id){
        Category category = categoryService.getCategory(id);
        return ResponseEntity.ok(category);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category){
        categoryService.create(category);
        return ResponseEntity.ok(category);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Category category){
        categoryService.create(category);
        return ResponseEntity.ok(category);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return ResponseEntity.ok("Delete Successfully");
    }
}
