package com.art.spring.database.repository;

import com.art.spring.bpp.Auditing;
import com.art.spring.bpp.InjectBean;
import com.art.spring.bpp.Transaction;
import com.art.spring.database.pool.ConnectionPool;
import com.art.spring.entity.Company;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @InjectBean
    private ConnectionPool connectionPool;

    @PostConstruct
    private void init() {
        System.out.println("init company repository...");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method...");
    }
}
