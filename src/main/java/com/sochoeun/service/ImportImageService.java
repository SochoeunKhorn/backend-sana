package com.sochoeun.service;

import com.sochoeun.entity.ImportImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImportImageService {
    void create(ImportImage req);
    List<ImportImage> getAllImportImage();
    ImportImage getById(String id);
    String uploadPhoto(String id, MultipartFile file);
}
