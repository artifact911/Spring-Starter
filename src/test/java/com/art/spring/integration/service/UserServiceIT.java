package com.art.spring.integration.service;

import com.art.spring.database.entity.Role;
import com.art.spring.dto.UserCreateEditDto;
import com.art.spring.dto.UserReadDto;
import com.art.spring.integration.IntegrationTestBase;
import com.art.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;
    private final UserService userService;

    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertEquals(5, result.size(), "Неверный размер");
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);

        assertAll(
                () -> assertTrue(maybeUser.isPresent()),
                () -> maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()))
        );
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );

        UserReadDto actualResult = userService.create(userDto);

        assertAll(
                () -> assertEquals(userDto.getUsername(), actualResult.getUsername()),
                () -> assertEquals(userDto.getBirthDate(), actualResult.getBirthDate()),
                () -> assertEquals(userDto.getFirstname(), actualResult.getFirstname()),
                () -> assertEquals(userDto.getLastname(), actualResult.getLastname()),
                () -> assertEquals(userDto.getCompanyId(), actualResult.getCompany().id()),
                () -> assertSame(userDto.getRole(), actualResult.getRole())
        );
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);
        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(user ->
                assertAll(
                        () -> assertEquals(userDto.getUsername(), user.getUsername()),
                        () -> assertEquals(userDto.getBirthDate(), user.getBirthDate()),
                        () -> assertEquals(userDto.getFirstname(), user.getFirstname()),
                        () -> assertEquals(userDto.getLastname(), user.getLastname()),
                        () -> assertEquals(userDto.getCompanyId(), user.getCompany().id()),
                        () -> assertSame(userDto.getRole(), user.getRole())
                )
        );
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(USER_1));
    }
}
