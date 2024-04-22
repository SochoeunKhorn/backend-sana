package com.sochoeun.repository;

import com.sochoeun.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content,Integer> {
    Page<Content> findAllByArticle_Id(Integer articleId, Pageable pageable);
}
