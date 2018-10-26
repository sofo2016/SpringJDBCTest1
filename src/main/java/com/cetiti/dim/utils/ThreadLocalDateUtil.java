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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDateUtil {
	
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

    public static DateFormat getDateFormat()
    {
        DateFormat df = threadLocal.get();
        if(df==null){
            df = new SimpleDateFormat(RetCode.DATEFORMAT);
            threadLocal.set(df);
        }
        return df;
    }

    public static DateFormat getDateFormat2()
    {
        DateFormat df = threadLocal.get();
        if(df==null){
            df = new SimpleDateFormat(RetCode.DATEFORMAT2);
            threadLocal.set(df);
        }
        return df;
    }
    public static DateFormat getDateFormat3()
    {
        DateFormat df = threadLocal.get();
        if(df==null){
            df = new SimpleDateFormat(RetCode.DATEFORMAT3);
            threadLocal.set(df);
        }
        return df;
    }

    public static String formatDateToString(Date date) throws ParseException {
        return getDateFormat().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }

    public static String formatDateToString2(Date date) throws ParseException {
        return getDateFormat2().format(date);
    }

    public static Date parse2(String strDate) throws ParseException {
        return getDateFormat2().parse(strDate);
    }
    
}