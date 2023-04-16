package com.art.spring.integration.database.repository;

import com.art.spring.database.entity.Role;
import com.art.spring.database.entity.User;
import com.art.spring.database.repository.UserRepository;
import com.art.spring.dto.UserFilter;
import com.art.spring.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void checkBatchNamed() {
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRoleNamed(users);
        System.out.println();
    }
    
    @Test
    void checkBatch() {
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
        System.out.println();
    }

    @Test
    void checkJdbcTemplate() {
        var users = userRepository.findAllByCompanyAndRole(1, Role.USER);
        assertEquals(1, users.size());
        System.out.println();
    }
    @Test
    void checkAuditing() {
        var ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1L));
        userRepository.flush();

        System.out.println();
    }

    @Test
    void checkCustomImpl() {
        // под queryDsl перепил
//        UserFilter filter = new UserFilter(null, "%ov%", LocalDate.now());
        UserFilter filter = new UserFilter(null, "ov", LocalDate.now());
        var users = userRepository.findAllByFilter(filter);

        System.out.println();
    }

    @Test
    void checkProjections() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
        System.out.println();
    }

    @Test
    void checkPageable() {
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var slice = userRepository.findAllBy(pageable);

        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        // а теперь мы можем получать следующий кусочек(страничку) и выборки, если он есть. При этом передав Pageble
        // из этого Slice, и получится страничка 2
        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkSort() {
//        var sortById = Sort.by("firstname").and(Sort.by("lastname"));

        var sortBy = Sort.sort(User.class);
        var sort = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));

        var allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFindTop3() {
        var allUsers = userRepository.findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate.now());
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop() {
        var topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkUpdate() {
        var ivan = userRepository.getById(1L);
        assertEquals(Role.ADMIN, ivan.getRole());

        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        var theSameIvan = userRepository.getById(1L);
        assertEquals(Role.USER, theSameIvan.getRole());
    }

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);

        System.out.println(users);
    }
}