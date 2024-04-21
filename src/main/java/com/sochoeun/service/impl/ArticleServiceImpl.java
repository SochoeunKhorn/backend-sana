package com.sochoeun.service.impl;

import com.sochoeun.entity.Article;
import com.sochoeun.entity.Category;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.ArticleRepository;
import com.sochoeun.service.ArticleService;
import com.sochoeun.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;
    @Override
    public void create(Article article) {
        Integer id = article.getId();
        if(id != null){
            getArticle(id);
        }
        Category category = categoryService.getCategory(article.getCategory().getId());
        if(category != null){
            articleRepository.save(article);
        }
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> getArticlesByCategoryId(Integer categoryId) {
        return articleRepository.findAllByCategory_Id(categoryId);
    }

    @Override
    public Article getArticle(Integer id) {
        return articleRepository.findById(id).orElseThrow(()->new NotFoundException("Article",id));
    }

    @Override
    public void delete(Integer id) {
        getArticle(id);
        articleRepository.deleteById(id);
    }
}
