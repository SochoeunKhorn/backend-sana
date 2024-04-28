package com.sochoeun.service;

import com.sochoeun.entity.Slide;

import java.util.List;

public interface SlideService {
    void create(Slide slide);
    Slide getSlide(Integer id);
    List<Slide> getSlides();
}
