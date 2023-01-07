package com.art.spring.service;

import com.art.spring.database.repository.CrudRepository;
import com.art.spring.dto.CompanyReadDto;
import com.art.spring.entity.Company;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> companyCrudRepository;
    private final UserService userService;

    public CompanyService(CrudRepository<Integer, Company> companyCrudRepository,
                          UserService userService) {
        this.companyCrudRepository = companyCrudRepository;
        this.userService = userService;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyCrudRepository.findById(id)
                .map(entity -> new CompanyReadDto(entity.id()));
    }
}
