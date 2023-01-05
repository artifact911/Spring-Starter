package com.art.spring.config;

import com.art.spring.database.repository.CrudRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
// откуда читать проперти
@PropertySource("classpath:application.properties")
// настройка компонентСкан:
// в каком пакете сканировать
@ComponentScan(basePackages = "com.art.spring",
// используем ли дефолтные фильтры при сканировании
useDefaultFilters = false,
// включаем фильтры для сканирования
includeFilters = {
        // по аннотации
        @Filter(type = FilterType.ANNOTATION, value = Component.class),
        // по классу
        @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
        // по регулярке
        @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
})
public class ApplicationConfiguration {
}
