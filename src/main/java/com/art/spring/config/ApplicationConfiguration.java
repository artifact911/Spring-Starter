package com.art.spring.config;

import com.art.spring.database.repository.CrudRepository;
import com.art.web.config.WebConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.*;

// комбинируем все три варианта определения BeanDefinitions добавляя xml
//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
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
