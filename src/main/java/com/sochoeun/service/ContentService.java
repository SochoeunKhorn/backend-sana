package com.sochoeun.service;

import com.sochoeun.entity.Content;
import com.sochoeun.entity.Image;
import com.sochoeun.entity.Media;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContentService {
    Content create(Content content);
    List<Content> getContents();
    Page<Content> getPageable(Integer id,Integer pageNo,Integer pageSize);
    Content getContent(Integer id);
    Content update(Integer id,Content content);
    void delete(Integer id);
    List<Image> getImages(Integer contentId);
    List<Media> getMedias(Integer contentId);
    Content getAllImagesContent(Integer contentId);

    // images
    void uploadImage(Image image);
    void uploadMedia(Media media);

}
