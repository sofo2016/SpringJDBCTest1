/*
 * File Name: MointorException.java
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

import java.io.PrintStream;

public class MonitorException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Throwable source;

    public MonitorException(Throwable e) {
        this.source = e;
    }

    public Throwable getSource() {
        return this.source;
    }

    public String getMessage() {
        return this.source.getMessage();
    }

    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        this.source.printStackTrace(s);
    }
}
