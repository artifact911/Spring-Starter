package com.art.spring.listener.entity;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

//    @EventListener
    // можем выставить Conditions образаясь к аргументам метода и в данном случае неявно геттером получил имя поля,
    // которое тут енам
    // теперь мы слушаем ТОЛЬКО READ события
    @EventListener(condition = "#root.args[0].accessType.name() == 'READ'")
    // можно определить порядок срабатывания листенеров:
    @Order(10)
    public void acceptEntityRead(EntityEvent entityEvent) {
        System.out.println("Entity: " + entityEvent);
    }
}
