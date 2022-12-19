package com.art.spring;

import com.art.spring.database.pool.ConnectionPool;
import com.art.spring.database.repository.CompanyRepository;
import com.art.spring.database.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {

            // clazz -> String -> Map<String, Object>
            var connectionPool = context.getBean("p3", ConnectionPool.class);
            System.out.println(connectionPool);

            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
