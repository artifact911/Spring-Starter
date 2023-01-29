package com.art.spring.database.repository;

import com.art.spring.database.entity.Role;
import com.art.spring.database.entity.User;
import com.art.spring.dto.PersonalInfo;
import com.art.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyAndRole(Integer companyId, Role role);
}
