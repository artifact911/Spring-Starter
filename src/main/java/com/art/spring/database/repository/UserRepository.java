package com.art.spring.database.repository;

import com.art.spring.database.entity.Role;
import com.art.spring.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Modifying(clearAutomatically = true,
    //необязательно, это в хибере по дефолту
    flushAutomatically = true )
    @Query("update User u " +
            "set u.role = :role " +
            "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    List<User> findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate birthDate);

    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    // заюзаем Slice
//    List<User> findAllBy(Pageable pageable);

    // заюзаем Page
//    Slice<User> findAllBy(Pageable pageable);
    @Query(value = "select u from User u",
    countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);
}
