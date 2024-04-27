package com.sochoeun.repository;

import com.sochoeun.entity.ImportImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportImageRepository extends JpaRepository<ImportImage,String> {
}
