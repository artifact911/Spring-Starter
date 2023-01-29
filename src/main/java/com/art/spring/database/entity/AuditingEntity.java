package com.art.spring.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
// это нужно, чтобы поля могли быть унаследованы
@MappedSuperclass
// чтобы поля автоматом обновлялись нужно прикрутить лисенер. Самому писать не нужно - есть у Спринга готовый
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingEntity<T extends Serializable> implements BaseEntity<T> {

    // указали куда что писать
    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant modifiedAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
}
