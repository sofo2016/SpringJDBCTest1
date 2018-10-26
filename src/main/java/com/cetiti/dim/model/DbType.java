/*
 * File Name: DbType.java
 * Copyright: (C) 2017 CETITI
 * Description: 
 * Author: chensj
 * Create Date: 2018-8-27 10:50
 * Modifier: lenovo
 * Modify Date: 2018-08-27
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dim.model;

import com.cetiti.dim.exception.MonitorSimpleException;

public  enum DbType{
    MySql(1),
    Oracle(2),
    GreenPlum(3);
    int code;

    DbType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static DbType valueOf(int code) {
        switch(code) {
            case 1:
                return MySql;
            case 2:
                return Oracle;
            case 3:
                return GreenPlum;
            default:
                throw new MonitorSimpleException(String.format("未知数据库类型,code[%s]", new Object[]{Integer.valueOf(code)}));
        }
    }
}