package com.art.spring;

import com.art.spring.database.pool.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");

        // clazz -> String -> Map<String, Object>
        var connectionPool = context.getBean("pool2", ConnectionPool.class);
        var connectionPoolByName = context.getBean("p4", ConnectionPool.class);
        System.out.println(connectionPool);
    }
}
