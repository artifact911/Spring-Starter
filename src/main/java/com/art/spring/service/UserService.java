package com.art.spring.service;

import com.art.spring.database.repository.CrudRepository;
import com.art.spring.database.repository.UserRepository;
import com.art.spring.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // тут будет прокси, поэтому мы должны юзать интерфейс CrudRepository.
    // т.к. у нас будет несколько реализаций CrudRepository, то при инициализации в данном случае, мы будем искать бин
    // на основании имени параметра нашего конструктора
    private final CrudRepository<Integer, Company> companyRepository;
}
