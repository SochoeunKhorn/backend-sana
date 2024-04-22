package com.sochoeun.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "contents")
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    // Image image;
    private LocalDate createAt;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
