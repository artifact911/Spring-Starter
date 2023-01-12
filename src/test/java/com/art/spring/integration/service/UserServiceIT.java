package com.art.spring.integration.service;

import com.art.spring.database.pool.ConnectionPool;
import com.art.spring.integration.annotation.IT;
import com.art.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

@IT
@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void test() {
        System.out.println();
    }

    @Test
    void test2() {
        System.out.println();
    }
}
