package com.art.spring.database.repository;

import com.art.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(
            nativeQuery = true,
            value = "SELECT u.* FROM users u WHERE u.username = :username"
    )
    List<User> findAllBy(String username);
}
