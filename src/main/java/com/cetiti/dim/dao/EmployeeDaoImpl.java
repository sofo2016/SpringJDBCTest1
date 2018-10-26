package com.cetiti.dim.dao;


import com.cetiti.dim.model.Employee;
import com.cetiti.dim.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanggenshen_sx on 2016/12/23.
 */
@Repository
public class EmployeeDaoImpl implements  IEmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> getInfo(int id){
        String sqlStr="SELECT id, lastName,email FROM employee WHERE id=?";
        List<Employee> employeeList = jdbcTemplate.query(sqlStr, new Object[]{id},
                new ResultSetExtractor<List>(){
                    public List extractData(ResultSet e) throws SQLException, DataAccessException {
                        List employees = new ArrayList();
                        while(e.next()) {
                            Employee employee = new Employee();
                            employee.setId(e.getInt("id"));
                            employee.setLastName(e.getString("lastName"));
                            employee.setEmail(e.getString("email"));
                            employees.add(employee);
                        }
                        return employees;
                    }});
        return employeeList;
    }
}