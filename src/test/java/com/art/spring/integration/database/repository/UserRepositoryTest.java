package com.art.spring.integration.database.repository;

import com.art.spring.database.repository.UserRepository;
import com.art.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkQueries() {

        //
        var users = userRepository.findAllBy("a", "ov");

        Assertions.assertThat(users).hasSize(3);

        System.out.println(users);
    }
}