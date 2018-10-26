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

import com.cetiti.dim.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@ResponseBody
@Controller
@RequestMapping("/employee")
@Scope("prototype")
public class EmployeeController {
@Autowired
private IEmployeeService iEmployeeService;
@RequestMapping(value = {"/getInfo"},produces ={"application/json"},method = RequestMethod.GET)
public String getId()
{
   // iEmployeeService.get(1);
    return  "hello world";
}
}
