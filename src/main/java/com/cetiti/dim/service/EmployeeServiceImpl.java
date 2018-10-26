/*
 * File Name: EmployeeService.java
 * Copyright: (C) 2017 CETITI
 * Description: 
 * Author: chensj
 * Create Date: 2018-10-26 14:47
 * Modifier: lenovo
 * Modify Date: 2018-10-26
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dim.service;

import com.cetiti.dim.dao.IEmployeeDao;
import com.cetiti.dim.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeDao iEmployeeDao;
    @Override
    public Employee get(int id) {
        return iEmployeeDao.get(1);
    }
}
