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

public final class RetCode {             // Compliant
		public static final int SUCCESS = 0x0000;
		public static final int FAILURE = 0xFFFF;
		public static final int SUCCESS_BUT_EMPTY = -1;
		public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
		public static final String DATEFORMAT2 = "yyyyMMddHHmmss";
		public static final String DATEFORMAT3 = "yyyy-MM-dd";
		public static final String DBDRIVER = "DB_DRIVER";
		public static final String DBDRIVERMYSQL = "com.mysql.jdbc.Driver";
		public static final String DBDRIVERORACLE = "oracle.jdbc.driver.OracleDriver";
	    public static final String DBDRIVERGREENPLUM = "com.pivotal.jdbc.GreenplumDriver";
		public static final String DBERROR ="未知数据库驱动,code[%s]";
		public static final String LOCALORREMOTE = urlConfig.getProperty("LOCAL_OR_REMOTE");
		public static final String ETLROOT = urlConfig.getProperty("ETL_ROOT");
		public static final String ETLSERVERIP = urlConfig.getProperty("ETL_SERVER_IP");
		public static final String ETLSERVERUSER = urlConfig.getProperty("ETL_SERVER_USER");
		public static final String ETLSERVERPASSWD = urlConfig.getProperty("ETL_SERVER_PASSWD");
		public static final String APPKEY = urlConfig.getProperty("appkey");
		public static final String APPSECRET = urlConfig.getProperty("appSecret");
		public static final String LOGURL = urlConfig.getProperty("logurl");
		public static final String INPUTDATASERVERIP= urlConfig.getProperty("INPUT_DATA_SERVER_IP");
		public static final String DATAINPUTSERVER_PORT = urlConfig.getProperty("DataInputServer_Port");
		public static final String DATACENTER_DB_DRIVER = urlConfig.getProperty("DATACENTER_DB_DRIVER");
		public static final String DATACENTER_DB_USER = urlConfig.getProperty("DATACENTER_DB_USER");
		public static final String DATACENTER_DB_PWD = urlConfig.getProperty("DATACENTER_DB_PWD");
		public static final String DATACENTER_URL = urlConfig.getProperty("DATACENTER_URL");
}