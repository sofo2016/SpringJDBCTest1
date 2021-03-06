/*
 * Copyright (C) 2017 CETITI
 * File Name:Utils.java
 * Description:
 * Author:Chensj
 * Create Date:2017-12-20
 * Modifier:Chensj
 * Modify Date:2017-12-20
 * Bugzilla Id: 
 * Modify Content:
 */
package com.cetiti.dim.utils;

public class Result {
    private int code;
    private String message;
    private int count;


    public int getRetCode() {
        return code;
    }

    public void setRetCode(int retCode) {
        this.code = retCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message, int retCode) {
        message += ",返回码:" + retCode;
        this.message = message;
        this.code = retCode;
    }

}
