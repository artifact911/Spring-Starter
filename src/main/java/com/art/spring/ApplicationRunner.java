package com.art.spring;

import com.art.spring.database.pool.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");

        // clazz -> String -> Map<String, Object>
        var connectionPoolByName = context.getBean("p3", ConnectionPool.class);
        System.out.println(connectionPoolByName);
    }
}
