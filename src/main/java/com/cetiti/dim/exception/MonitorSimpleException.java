/*
 * File Name: MonitorSimpleException.java
 * Copyright: (C) 2017 CETITI
 * Description: 
 * Author: 
 * Create Date: 
 * Modifier: geshengbin
 * Modify Date: 2017-12-21
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dim.exception;

public class MonitorSimpleException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorMessage_;

    public MonitorSimpleException(String errorMessage) {
        this.errorMessage_ = errorMessage;
    }

    public String toString() {
        return this.errorMessage_ + " " + super.toString();
    }

    public String getMessage() {
        return this.errorMessage_;
    }
}
