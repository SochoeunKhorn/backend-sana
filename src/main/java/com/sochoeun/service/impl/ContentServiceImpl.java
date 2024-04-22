package com.sochoeun.service.impl;

import com.sochoeun.entity.Article;
import com.sochoeun.entity.Content;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.pagination.PageDTO;
import com.sochoeun.pagination.PageUtil;
import com.sochoeun.repository.ContentRepository;
import com.sochoeun.service.ArticleService;
import com.sochoeun.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final ArticleService articleService;
    @Override
    public Content create(Content content) {
        articleService.getArticle(content.getArticle().getId());
        return contentRepository.save(content);
    }

    @Override
    public List<Content> getContents() {
        return contentRepository.findAll();
    }

    @Override
    public Page<Content> getPageable(Integer id, Integer pageNo, Integer pageSize) {
        articleService.getArticle(id);
        Pageable pageable = PageUtil.getPageable(pageNo,pageSize);
        return contentRepository.findAllByArticle_Id(id,pageable);
    }

    @Override
    public Content getContent(Integer id) {
        return contentRepository.findById(id).orElseThrow(()->new NotFoundException("Content",id));
    }

    @Override
    public Content update(Integer id, Content content) {
        articleService.getArticle(content.getArticle().getId());
        Content update = getContent(id);
        update.setArticle(articleService.getArticle(content.getArticle().getId()));
        update.setTitle(content.getTitle());
        update.setDescription(content.getDescription());
        update.setCreateAt(update.getCreateAt());
        return contentRepository.save(update);
    }

    @Override
    public void delete(Integer id) {
        if(getContent(id) != null){
            contentRepository.deleteById(id);
        }
    }
}
