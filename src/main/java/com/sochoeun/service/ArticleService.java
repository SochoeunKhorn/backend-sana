package com.sochoeun.service;

import com.sochoeun.entity.Article;

import java.util.List;

public interface ArticleService {
    void create(Article article);
    List<Article> getArticles();
    List<Article> getArticlesByCategoryId(Integer categoryId);
    Article getArticle(Integer id);
    void delete(Integer id);
}
