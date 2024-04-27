package com.sochoeun.service;

import com.sochoeun.entity.Category;

import java.util.List;

public interface CategoryService {
    void create(Category category);
    List<Category> getAllCategory();
    Category getCategory(Integer id);
    void delete(Integer id);
}
