package com.art.spring;

import com.art.spring.config.ApplicationConfiguration;
import com.art.spring.database.pool.ConnectionPool;
import com.art.spring.database.repository.CrudRepository;
import com.art.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        // переход к классу конфигурации
//        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();
            // clazz -> String -> Map<String, Object>
            var connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);

            var companyService = context.getBean(CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}
