package com.sochoeun.service.impl;

import com.sochoeun.entity.Content;
import com.sochoeun.entity.Image;
import com.sochoeun.entity.Media;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.pagination.PageUtil;
import com.sochoeun.repository.ContentRepository;
import com.sochoeun.repository.ImageRepository;
import com.sochoeun.repository.MediaRepository;
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
    private final ImageRepository imageRepository;
    private final MediaRepository mediaRepository;
    @Override
    public Content create(Content content) {
        articleService.getArticle(content.getArticle().getId());
        Content ct = contentRepository.save(content);
        if(content.getId() != 0){
            content.getImageList().forEach(image -> {
                image.setContentId(ct.getId());
                imageRepository.save(image);
            });
            content.getMediaList().forEach(media -> {
                media.setContentId(ct.getId());
                mediaRepository.save(media);
            });
        }
        return ct;
    }

    @Override
    public List<Content> getContents() {
        List<Content> all = contentRepository.findAll();
        all.forEach(image->{
            image.setImageList( getImages(image.getId()));
        });
        all.forEach(media->{
            media.setMediaList(getMedias(media.getId()));
        });
        return all;
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

    @Override
    public List<Image> getImages(Integer contentId) {
        return imageRepository.findAllByContentId(contentId);
    }

    @Override
    public List<Media> getMedias(Integer contentId) {
        return mediaRepository.findAllByContentId(contentId);
    }

    @Override
    public Content getAllImagesContent(Integer contentId) {
        Content content = getContent(contentId);
        content.setImageList(getImages(contentId));
        return content;
    }

}
