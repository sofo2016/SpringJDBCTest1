/*
 * File Name: EmployeeController.java
 * Copyright: (C) 2017 CETITI
 * Description: 
 * Author: chensj
 * Create Date: 2018-10-26 14:47
 * Modifier: lenovo
 * Modify Date: 2018-10-26
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dim.controller;

import com.cetiti.dim.model.Employee;
import com.cetiti.dim.service.IEmployeeService;
import com.cetiti.dim.utils.ListResult;
import com.cetiti.dim.utils.Result;
import com.cetiti.dim.utils.RetCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping("/employee")
@Scope("prototype")
public class EmployeeController {
    private static Logger logger = Logger.getLogger(EmployeeController.class);
    @Autowired
    private IEmployeeService iEmployeeService;

    @RequestMapping(value = {"/getInfo"},produces ={"application/json"},method = RequestMethod.GET)
    public ListResult getInfo()
    {
        ListResult result = new ListResult();
        try{
            List<Employee> employeeList= iEmployeeService.getInfo(123);
            result.setRows(employeeList);
            result.setMessage("查询员工信息成功", RetCode.SUCCESS);
        } catch (Exception e) {
            result.setMessage("查询员工信息失败",RetCode.FAILURE);
            logger.error("getInfo is error:" + e);
            e.printStackTrace();
        }
        return  result;
    }
}
