package com.art.spring.database.repository;

import com.art.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // Заюзать можно: Optional, Entity, Future
//    @Query(name = "Company.findByName")
    @Query("select c from Company c " +
            "join fetch c.locales cl " +
            "where c.name = :name2")
    Optional<Company> findByName(@Param("name2") String name);

    // Containing - аналог LIKE но с %слеваИсправа
    // IgnoreCase - без учета регистра
    // заюзать можно Collection, Stream(batch, close)
    List<Company> findAllByNameContainingIgnoreCase(String fragment);
}
