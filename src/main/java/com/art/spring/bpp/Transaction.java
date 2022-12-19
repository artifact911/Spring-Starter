package com.art.spring.bpp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ставить над типом(классом)
 * Дла таких классов, перед вызовом их методов будет открываться транзакция -> выполняться метод -> закрывать транзакцию
 * Транзакцию будем эмулировать, но не суть
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Transaction {
}
