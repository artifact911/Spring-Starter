package com.art.spring.service;

import com.art.spring.database.repository.CrudRepository;
import com.art.spring.dto.CompanyReadDto;
import com.art.spring.entity.Company;
import com.art.spring.listener.entity.AccessType;
import com.art.spring.listener.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CrudRepository<Integer, Company> companyCrudRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyCrudRepository.findById(id)
                .map(entity ->
                {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                   return new CompanyReadDto(entity.id());
                });
    }
}
