/*
 * File Name: IEmployeeDao.java
 * Copyright: (C) 2017 CETITI
 * Description: 
 * Author: chensj
 * Create Date: 2018-10-26 14:49
 * Modifier: lenovo
 * Modify Date: 2018-10-26
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dim.dao;

import com.cetiti.dim.model.Employee;

public interface IEmployeeDao {
    public Employee get(int id);
}
