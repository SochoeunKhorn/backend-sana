package com.sochoeun.service.impl;

import com.sochoeun.entity.Slide;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.SlideRepository;
import com.sochoeun.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideRepository;
    @Override
    public void create(Slide slide) {
        slideRepository.save(slide);
    }

    @Override
    public Slide getSlide(Integer id) {
        return slideRepository.findById(id).orElseThrow(()->new NotFoundException("Slide",id));
    }

    @Override
    public List<Slide> getSlides() {
        return slideRepository.findAll();
    }
}
