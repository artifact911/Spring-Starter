package com.art.spring.database.repository;

import com.art.spring.database.entity.User;
import com.art.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}
