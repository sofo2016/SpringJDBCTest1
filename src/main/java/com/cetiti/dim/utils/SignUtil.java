/*
 * Copyright (C) 2017 CETITI
 * File Name:SignUtil.java
 * Description:
 * Author:Chensj
 * Create Date:2017-12-20
 * Modifier:Chensj
 * Modify Date:2017-12-20
 * Bugzilla Id: 
 * Modify Content:
 */
package com.cetiti.dim.utils;

import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class SignUtil
{
    /**
     * 根据请求参数map和秘钥appSecret生成签名
     * 
     * @param map
     *            接口的所有请求参数，包含appKey
     * @param appSecret
     *            单点登录发放给用户的appSecret
     * 
     *            Map<String, String> map = new HashMap<>(); map.put("appKey",
     *            "7e21cb45602c47bf428b471caa77f021"); map.put("account",
     *            "test"); map.put("password", "123456"); map.put("timestamp",
     *            String.valueOf(System.currentTimeMillis()));
     * 
     *            String sign = SignUtil.getSignature(map,
     *            "5d3be3eb7b9aa6412201a76283f1948e");
     * @return
     */
    public static String getSignature(Map<String, String> map, String appSecret)
    {
	Map<String, String> sortedParams = new TreeMap<String, String>(map);
	Set<Entry<String, String>> entrys = sortedParams.entrySet();

	StringBuilder sb = new StringBuilder();

	sb.append(appSecret);
	for (Entry<String, String> param : entrys)
	{
	    if (null != param.getValue())
	    {
		sb.append(param.getKey()).append(param.getValue().trim());
	    }
	}
	sb.append(appSecret);

	return md5(sb.toString());
    }

    public static String md5(String s)
    {
	String retn = null;
	if (null == s || s.length() < 1)
	{
	    return retn;
	}
	try
	{
	    MessageDigest md5 = MessageDigest.getInstance("MD5");
	    byte[] bytes = md5.digest(s.getBytes("UTF-8"));
	    retn = toHexString(bytes);
	} catch (Exception e)
	{
	    throw new RuntimeException(e);
	}

	return retn;
    }

    private static String toHexString(byte[] bytes)
    {
	StringBuilder retn = new StringBuilder();
	for (int i = 0; i < bytes.length; i++)
	{
	    String hex = Integer.toHexString(bytes[i] & 0xFF);
	    if (hex.length() == 1)
	    {
		retn.append("0");
	    }
	    retn.append(hex);
	}

	return retn.toString();
    }

}
