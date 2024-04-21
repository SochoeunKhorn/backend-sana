package com.sochoeun.service;

import com.sochoeun.entity.Article;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    void create(Article article);
    List<Article> getArticles();
    Page<Article> getArticlesByCategoryId(Integer categoryId,Integer pageNo,Integer pageSize);
    Article getArticle(Integer id);
    void delete(Integer id);
}
