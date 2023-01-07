package com.art.spring.config;

import com.art.spring.database.pool.ConnectionPool;
import com.art.spring.database.repository.CrudRepository;
import com.art.spring.database.repository.UserRepository;
import com.art.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.*;

// комбинируем все три варианта определения BeanDefinitions добавляя xml
//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
public class ApplicationConfiguration {

    @Bean("pool2")
    // Singleton по-умолчанию, но можем менять
//    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 25);
    }

    @Bean
    @Profile("prod|web")
    public UserRepository userRepository2(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    public UserRepository userRepository3() {
        return new UserRepository(pool3());
    }
}
