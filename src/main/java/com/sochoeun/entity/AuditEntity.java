package com.sochoeun.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {
    @CreatedDate()
    @Column(updatable = false)
    private LocalDateTime dateCreated;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime dateUpdated;
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @LastModifiedBy()
    @Column(insertable = false)
    private String updatedBy;
}
