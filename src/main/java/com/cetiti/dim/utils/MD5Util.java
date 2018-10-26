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

import com.cetiti.dim.exception.MonitorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    static Log log = LogFactory.getLog(MD5Util.class);

    public static String md5(String sourceStr) {
        String result = "";
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(sourceStr.getBytes());
            byte[] b = e.digest();
            StringBuffer buf = new StringBuffer("");
            for(int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if(i < 0) {
                    i += 256;
                }
                if(i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            return result;
        } catch (NoSuchAlgorithmException e) {
            throw new MonitorException(e);
        }
    }
}
