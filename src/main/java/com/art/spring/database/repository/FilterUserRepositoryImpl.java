package com.art.spring.database.repository;

import com.art.spring.database.entity.User;
import com.art.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {

        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);

        var root = criteria.from(User.class);
        criteria.select(root);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.firstname() != null) {
            predicates.add(cb.like(root.get("firstname"), filter.firstname()));
        }
        if (filter.lastname() != null) {
            predicates.add(cb.like(root.get("lastname"), filter.lastname()));
        }
        if (filter.birthDate() != null) {
            predicates.add(cb.lessThan(root.get("birthDate"), filter.birthDate()));
        }

        criteria.where(predicates.toArray(Predicate[]::new));
        return entityManager.createQuery(criteria).getResultList();
    }
}
