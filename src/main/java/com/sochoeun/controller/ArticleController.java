package com.sochoeun.controller;

import com.sochoeun.entity.Article;
import com.sochoeun.pagination.PageDTO;
import com.sochoeun.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<?> getArticlesByCategory(@PathVariable("category_id") Integer category_id,
                                                   @RequestParam(value = "pageNo",required = false,defaultValue = "0") Integer pageNo,
                                                   @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize){
        Page<Article>page=articleService.getArticlesByCategoryId(category_id,pageNo,pageSize);
        PageDTO pageDTO = new PageDTO(page);
        return ResponseEntity.ok(pageDTO);
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
