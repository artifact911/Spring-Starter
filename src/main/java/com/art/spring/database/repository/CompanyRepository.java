package com.art.spring.database.repository;

import com.art.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // Заюзать можно: Optional, Entity, Future
    Optional<Company> findByName(String name);

    // Containing - аналог LIKE но с %слеваИсправа
    // IgnoreCase - без учета регистра
    // заюзать можно Collection, Stream(batch, close)
    List<Company> findAllByNameContainingIgnoreCase(String fragment);
}
