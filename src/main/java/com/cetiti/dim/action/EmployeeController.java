package com.cetiti.dim.action;

import com.cetiti.dim.dao.EmployeeDao;
import com.cetiti.dim.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by wanggenshen_sx on 2016/12/23.
 */
@Controller
public class EmployeeController {

    private ApplicationContext ctx=null;
    private JdbcTemplate jdbcTemplate=null;
    private EmployeeDao employeeDao ;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate=null;
    {
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate =(JdbcTemplate) ctx.getBean("jdbcTemplate");
        employeeDao = ctx.getBean(EmployeeDao.class);
        namedParameterJdbcTemplate=(NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");

    }

    @RequestMapping(value = "/listAllEmployee",method = RequestMethod.GET)
    public String list(Map<String,Object> map){
        String sql="SELECT id, lastName,email FROM employee";
        RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);
        List<Employee> employees=jdbcTemplate.query(sql, rowMapper);
        map.put("employees",employees);
        return "list";
    }
}