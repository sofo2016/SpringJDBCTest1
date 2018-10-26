/*
 * File Name: ProjectPathUtil.java
 * Copyright: Copyright 2012-2013 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: zhouxinghua
 * Create Date: 2013-8-28
 * Modifier: zhouxinghua
 * Modify Date: 2013-9-12
 * Bugzilla Id:  
 * Modify Content: change the way of get project path
 */
package com.cetiti.dim.common;

import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Convenience class for manage project path and get file object by relative path,
 * java project and web project use the same function.
 * @author    zhouxinghua
 * @version   DDAP V1.0.0, 2013-8-20
 * @see       
 * @since     DDAP V1.0.0
 */
public class ProjectPathUtil
{
    /**
     * get root path of the current project,
     * use the directory of project as root path when used in java project,
     * use the directory of WEB-INF folder or classes parent's folder as root path when used in Web project,
     * use the directory of jar file when the project is packed into a jar file.
     * @return root path
     */
    public static String getProjectPath()
    {
        URL fileUrl = ProjectPathUtil.class.getProtectionDomain().getCodeSource().getLocation();
        String path = fileUrl.getPath();
        int index = path.lastIndexOf("WEB-INF");
        if (index == -1)
        {
            index = path.lastIndexOf("classes");
        }
        else
        {
            index = index + 8;
        }
        if (index == -1)
        {
            index = path.lastIndexOf("bin");
        }
        if(index == -1)
        {
            index = path.lastIndexOf("lib");
        }
        if(index == -1)
        {
            index = path.lastIndexOf("/") + 1;
        }
        path = path.substring(0, index); 
        /*//class file in war, path is like "zip:D:/..."
        if (path.startsWith("zip"))
        {
            path = path.substring(4);
        }
        //class file in classes folder, path is like "file:/D:/..."
        else if (path.startsWith("file"))
        {
            path = path.substring(5);
        }
        // class file in jar, path is like "jar:file:/D:/..."
        else if (path.startsWith("jar"))
        {
            path = path.substring(9);
        } */
        String realPath = null;
        try
        {
            realPath = java.net.URLDecoder.decode(path, "UTF-8");
        }
        catch (java.io.UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return realPath;
    }
    /**
     * change the real path to windows style path
     * @param realPath
     * @return null if realPath is null
     */
    public static String adaptPathOnWindows(String realPath)
    {
        String regWin = "[a-z|A-Z]:([\\\\|/]\\w+)+[\\\\|/]\\w*(.\\w*|)"; 
//        String regUinx = "/(\\w+/)*\\w+(\\.\\w+)";
        String winPath = null;
        if(StringUtils.isNotBlank(realPath))
        {
            if(realPath.matches(regWin))
            {
                winPath = realPath;
            }
            else
            {
                realPath = realPath.replaceAll("//", "/");
                if(realPath.charAt(0) == '/')
                    realPath = realPath.substring(1);
                winPath = realPath.replaceAll("/", "\\\\");
            }
        }
        return winPath;
    }
   
    /**
     * get the Properties object by relative path,
     * using project's root path as the reference path.
     * @param relativePath
     * @return Properties
     * @throws RuntimeException
     */
    public static Properties getProperties(String relativePath)
    {
        String filePath = null;
        if(relativePath.startsWith("./"))
            relativePath = relativePath.substring(2);
        else if(relativePath.startsWith("/") || relativePath.startsWith("."))
            relativePath = relativePath.substring(1);
        filePath = getProjectPath()+relativePath;        
        Properties properties = new Properties();
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(filePath);
            properties.load(in);
            in.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("couldn't load properties file '"
                + filePath + "'", e);
        }
        return properties;
    }
    /**
     * write the Properties object into the Properties file
     * @param pros object
     * @param relativePath Path
     * @throws RuntimeException
     */
    public static void saveProperties(Properties pros, String relativePath) 
    {
        OutputStream fos = null;
        File file = getFileByWorkRoot(relativePath);
        try
        {
            fos = new FileOutputStream(file);
            pros.store(fos, "update");
            fos.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("couldn't save properties file '"
                + relativePath + "'", e);
        }
    }
    
    /**
     * get the file object by relative path,
     * using project's root path as the reference path.
     * @param relativePath
     * @return File object
     */
    public static File getFileByWorkRoot(String relativePath)
    {
        String rootPath = getProjectPath();
        String filePath = null;
        if(relativePath.startsWith("./"))
            relativePath = relativePath.substring(2);
        else if(relativePath.startsWith("/") || relativePath.startsWith("."))
            relativePath = relativePath.substring(1);
        filePath = rootPath+relativePath;
        File file = new File(filePath);
        return file;
    }
    /*
    public static void main(String[] args) {

        System.out.println("Project Root:"+getProjectPath());
        Properties pros = getProperties("config/apply.properties");
        System.out.println(pros.getProperty("node.applyIP"));
        File test = getFileByWorkRoot("test.txt");
        if(test.exists())
        {
            System.err.println("Has such file");
        }
        else
        {
            System.err.println("No such file");
        }
    }
    */
}
