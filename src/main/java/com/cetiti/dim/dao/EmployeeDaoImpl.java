package com.cetiti.dim.dao;


import com.cetiti.dim.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by wanggenshen_sx on 2016/12/23.
 */
@Repository
public class EmployeeDaoImpl implements  IEmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employee get(int id){
        String sql="SELECT id, lastName,email FROM employee WHERE id=?";
        RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);
        Employee employee=jdbcTemplate.queryForObject(sql, rowMapper,id);
        return employee;
    }
}