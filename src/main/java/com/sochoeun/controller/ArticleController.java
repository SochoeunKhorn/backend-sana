package com.sochoeun.controller;

import com.sochoeun.entity.Article;
import com.sochoeun.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Article article){
        articleService.create(article);
        return ResponseEntity.ok(article);
    }
    @GetMapping
    public ResponseEntity<?> getArticles(){
        return ResponseEntity.ok(articleService.getArticles());
    }
    @GetMapping("/category/{category_id}")
    public ResponseEntity<?> getArticlesByCategory(@PathVariable("category_id") Integer category_id){
        List<Article> articlesByCategoryId = articleService.getArticlesByCategoryId(category_id);
        return ResponseEntity.ok(articlesByCategoryId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getArticle(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleService.getArticle(id));
    }
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Article article){
        articleService.create(article);
        return ResponseEntity.ok(article);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        articleService.delete(id);
        return ResponseEntity.ok("Delete Successfully");
    }
}
