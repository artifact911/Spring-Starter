package com.art.spring.service;

import com.art.spring.database.repository.CrudRepository;
import com.art.spring.database.repository.UserRepository;
import com.art.spring.entity.Company;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    // тут будет прокси, поэтому мы должны юзать интерфейс CrudRepository.
    // т.к. у нас будет несколько реализаций CrudRepository, то при инициализации в данном случае, мы будем искать бин
    // на основании имени параметра нашего конструктора
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
