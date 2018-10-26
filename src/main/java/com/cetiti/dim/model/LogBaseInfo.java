/*
 * File Name: LogBaseInfo.java
 * Copyright: (C) 2017 CETITI
 * Description: 
 * Author: 
 * Create Date: 
 * Modifier: geshengbin
 * Modify Date: 2017-12-21
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dim.model;
import com.cetiti.dim.utils.Pagination;

public class LogBaseInfo extends Pagination
{

    Long id;
    String operateModule;
    String operateContent;
    protected String createTime;
    String userName;
    String ipAddress;
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getOperateModule()
    {
        return operateModule;
    }
    public void setOperateModule(String operateModule)
    {
        this.operateModule = operateModule;
    }
    public String getOperateContent()
    {
        return operateContent;
    }
    public void setOperateContent(String operateContent)
    {
        this.operateContent = operateContent;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getIpAddress()
    {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

}
