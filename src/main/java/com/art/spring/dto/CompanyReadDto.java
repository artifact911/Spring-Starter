package com.art.spring.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CompanyReadDto {

    private final Integer id;

    public CompanyReadDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
