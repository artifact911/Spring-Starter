package com.art.spring.database.repository;

import com.art.spring.bpp.InjectBean;
import com.art.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;
}
