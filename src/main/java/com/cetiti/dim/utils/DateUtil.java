/*
 * Copyright (C) 2017 CETITI
 * File Name:DateUtil.java
 * Description:
 * Author:Chensj
 * Create Date:2017-12-20
 * Modifier:Chensj
 * Modify Date:2017-12-20
 * Bugzilla Id: 
 * Modify Content:
 */
package com.cetiti.dim.utils;

import com.cetiti.dim.exception.MonitorSimpleException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil{

    DateUtil(){  // default implementation ignored
    }


    public static String getTodayDate() throws ParseException{
    	Date date = new Date();
        return getDateString(date, RetCode.DATEFORMAT3);
    }
    
    public static String getDateString(Date date) throws ParseException{
        return getDateString(date, RetCode.DATEFORMAT);
    }



    public static String getDateDay(Date date) throws ParseException{
        return getDateString(date, RetCode.DATEFORMAT3);
    }



    public static String getDateString(Date date, String format) throws ParseException{
        if(date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            String dateString = formatter.format(date);
            return dateString;
        } else {
            return null;
        }
    }

    public static String transferDataToString(Date d) throws ParseException {
        SimpleDateFormat DATA_FORMAT_yyyyMMddHHmmss = new SimpleDateFormat(RetCode.DATEFORMAT);
        return DATA_FORMAT_yyyyMMddHHmmss.format(d);
    }

    public static String transferDataToString(Date d, String formate) throws ParseException{
        SimpleDateFormat FORMAT = new SimpleDateFormat(formate);
        return FORMAT.format(d);
    }



    public static Date getDatebyString(String strDate) throws ParseException{
        if(strDate != null && !"".equals(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat(RetCode.DATEFORMAT3);
            try {
                return formatter.parse(strDate);
            } catch (ParseException var3) {
                var3.printStackTrace();
            }
        }
        return null;
    }

    public static Date getDatebyStringyyyyMMddHHmmss(String strDate) throws ParseException{
        if(strDate != null && !"".equals(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat(RetCode.DATEFORMAT2);
            try {
                return formatter.parse(strDate);
            } catch (ParseException var3) {
                var3.printStackTrace();
            }
        }
        return null;
    }



public static int getLastDayOfMonth(int year, int month) throws ParseException{
       Calendar cal = Calendar.getInstance();
       cal.set(Calendar.YEAR, year);
       cal.set(Calendar.MONTH, month-1);
       // 某年某月的最后一天
       return cal.getActualMaximum(Calendar.DATE);
   }



public static int getPreDayOfMonth(int year, int month) throws ParseException{
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month-1);
    // 某年某月的最后一天  
    return cal.getActualMinimum(Calendar.DATE);

}



public static String getPastDate(int past){
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
    Date today = calendar.getTime();
    SimpleDateFormat format = new SimpleDateFormat(RetCode.DATEFORMAT3);
    String result = format.format(today);
    return result;
}  



public static String getDayOfEndTime() throws ParseException{
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE,-1);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DATE);
    calendar.set(year, month, day, 23, 59, 59);
    SimpleDateFormat dateformat = new SimpleDateFormat(RetCode.DATEFORMAT);
    String sDateSuffix = dateformat.format(calendar.getTime());
    return sDateSuffix;
}  


public static String getPreMonth(String repeatDate) throws ParseException{
    String lastMonth = "";
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
    int year = Integer.parseInt(repeatDate.substring(0, 4));
    String monthsString = repeatDate.substring(5, 7);
    int month;
    if ("0".equals(monthsString.substring(0, 1))) {
        month = Integer.parseInt(monthsString.substring(1, 2));
    } else {
        month = Integer.parseInt(monthsString.substring(0, 2));
    }
    cal.set(year,month,Calendar.DATE);
    lastMonth = dft.format(cal.getTime());
    return lastMonth;
}
 static String time = null;
 static String mysqlTimeString = "'"+time+"'";
 static String oralceTimeString = "TO_DATE('"+time+"','YYYY-MM-DD HH24:mi:ss')";

public static String getSqlNowString(){
    Date now = new Date();

    try {
        time = ThreadLocalDateUtil.formatDateToString(now);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    String str= urlConfig.getProperty(RetCode.DBDRIVER);
    switch(str) {
        case RetCode.DBDRIVERMYSQL:
            return mysqlTimeString;
        case RetCode.DBDRIVERORACLE:
            return oralceTimeString;
        default:
            throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
    }
}



public static String getSqlTimeString(Date date){
    String time = null;
    try {
        time = ThreadLocalDateUtil.formatDateToString(date);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    String str= urlConfig.getProperty(RetCode.DBDRIVER);
    switch(str) {
        case RetCode.DBDRIVERMYSQL:
            return mysqlTimeString;
        case RetCode.DBDRIVERORACLE:
            return oralceTimeString;
        default:
            throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
    }
}

public static String getSqlTimeString(String time){
    String str= urlConfig.getProperty(RetCode.DBDRIVER);
    switch(str) {
        case RetCode.DBDRIVERMYSQL:
            return mysqlTimeString;
        case RetCode.DBDRIVERORACLE:
            return oralceTimeString;
        default:
            throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
    }
}

public static String getSqlTodayYYYY_MM_DD(){
    String str= urlConfig.getProperty(RetCode.DBDRIVER);
    switch(str) {
        case RetCode.DBDRIVERMYSQL:
            return "DATE(now())";
        case RetCode.DBDRIVERORACLE:
            return "TRUNC(SYSDATE)";
        default:
            throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
    }
}


public static String getSqlTO_DATE(String date){
    String str= urlConfig.getProperty(RetCode.DBDRIVER);
    switch(str) {
        case RetCode.DBDRIVERMYSQL:
            return "STR_TO_DATE("+date+",'%Y-%m-%d')";
        case RetCode.DBDRIVERORACLE:
            return "TO_DATE("+date+",'YYYY-MM-DD')";
        default:
            throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
    }
}
    public static String getSqlTO_CHAR(String date) {
        String str= urlConfig.getProperty(RetCode.DBDRIVER);
        switch(str) {
            case RetCode.DBDRIVERMYSQL:
                return "DATE("+date+")";
            case RetCode.DBDRIVERORACLE:
                return "TO_CHAR("+date+",'YYYY-MM-DD')";
            default:
                throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
        }    
}


    public static String getSqlTruncStr(String str){
        String dbdriver= urlConfig.getProperty(RetCode.DBDRIVER);
        switch(dbdriver) {
            case RetCode.DBDRIVERMYSQL:
                return "DATE("+str+")";
            case RetCode.DBDRIVERORACLE:
                return "TRUNC("+str+")";
            default:
                throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
                }
        }    
        
    public static String getSqlTruncStrSubStr(String str){
        String dbdriver= urlConfig.getProperty(RetCode.DBDRIVER);
        switch(dbdriver) {
                case RetCode.DBDRIVERMYSQL:
                    return str;
                case RetCode.DBDRIVERORACLE:
                    return str.substring(0,str.length()-11);
                default:
                    throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
            }     
     }

    public static String getSqlDaysAgo(int inter){
        String dbdriver= urlConfig.getProperty(RetCode.DBDRIVER);
        switch(dbdriver) {
                case RetCode.DBDRIVERMYSQL:
                    return "DATE_SUB(DATE(NOW()),INTERVAL "+inter+" DAY)";
                case RetCode.DBDRIVERORACLE:
                    return "TRUNC(SYSDATE)-"+inter;
                default:
                    throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{inter}));
            }     
     }

    
    public static Timestamp getSqlTimeNow(){
       Date now = new Date();
       Timestamp time= new Timestamp(now.getTime());
       return time;
   }
}

