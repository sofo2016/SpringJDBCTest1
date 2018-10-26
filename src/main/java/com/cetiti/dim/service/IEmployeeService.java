/*
 * File Name: IEmployeeService.java
 * Copyright: (C) 2017 CETITI
 * Description: 
 * Author: chensj
 * Create Date: 2018-10-26 14:48
 * Modifier: lenovo
 * Modify Date: 2018-10-26
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dim.service;

import com.cetiti.dim.model.Employee;

import java.util.List;

public interface IEmployeeService {
    public List<Employee> getInfo(int id);
}
