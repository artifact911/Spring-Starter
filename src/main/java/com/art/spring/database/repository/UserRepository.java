package com.art.spring.database.repository;

import com.art.spring.database.entity.Role;
import com.art.spring.database.entity.User;
import com.art.spring.dto.PersonalInfo;
import com.art.spring.dto.PersonalInfo2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
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
            flushAutomatically = true)
    @Query("update User u " +
            "set u.role = :role " +
            "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    List<User> findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate birthDate);

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.NONE)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    // заюзаем Slice
//    List<User> findAllBy(Pageable pageable);

    // заюзаем Page
//    Slice<User> findAllBy(Pageable pageable);

    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "select u from User u",
            countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);

//    List<PersonalInfo> findAllByCompanyId(Integer companyId);

//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);


    @Query(
            value = "SELECT firstname, " +
                    "lastname, " +
                    "birth_date birthDate" + // birthDate - alias, чтоб интерфейса метод отработал
                    " FROM users WHERE company_id = :companyId",
            nativeQuery = true
    )
    List<PersonalInfo2> findAllByCompanyId(Integer companyId);

}
