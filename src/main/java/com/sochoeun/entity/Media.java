package com.sochoeun.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medias")
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mediaUrl;
    private int contentId;
}
