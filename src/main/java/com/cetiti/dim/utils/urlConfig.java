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

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class urlConfig {
    private static Logger logger = Logger.getLogger(urlConfig.class);

    public static Properties config = new Properties();

    public static String getProperty(String key) {
        return config.getProperty(key);
    }

  // 获取连接字符串

    static {
        try {
            //config.load(urlConfig.class.getResourceAsStream("/config/monitor.properties"));
        	config.load(new FileInputStream(new File(System.getProperty("user.home")+ System.getProperty("file.separator")+ "dxip"+ System.getProperty("file.separator") + "system.properties")));	
        } catch (Exception e) {
            logger.error("Cannot load properties:", e);
        }
    }
}

