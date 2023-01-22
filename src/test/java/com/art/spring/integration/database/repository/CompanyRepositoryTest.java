package com.art.spring.integration.database.repository;

import com.art.spring.database.entity.Company;
import com.art.spring.database.repository.CompanyRepository;
import com.art.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
// закомментил для ручного управления - позже в @IT запихнули
//@Transactional
// по умолчанию
//@Rollback
@Commit
class CompanyRepositoryTest {

    private static final Integer APPLE_ID = 5;

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    void delete() {
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());

        maybeCompany.ifPresent(companyRepository::delete);
        // delete() не вызовется, пока мы не закоммитим транзакцию либо нее вызовем flush() явно
        entityManager.flush();

        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(tx -> {
            var company = entityManager.find(Company.class, 1);
            assertNotNull(company);

            assertThat(company.getLocales()).hasSize(2);
        });
    }

    @Test
    void save() {
        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                )).build();

        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}