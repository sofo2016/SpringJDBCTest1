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

import com.cetiti.dim.common.HttpUtil;
import com.cetiti.dim.exception.MonitorException;
import com.cetiti.dim.exception.MonitorSimpleException;
import com.cetiti.dim.model.DbType;
import com.cetiti.dim.model.LogCondition;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils{
    private static Logger logger = Logger.getLogger(Utils.class);
	static long getTicks() {
		Date dt = new Date();
		Long time = dt.getTime();// 这就是距离1970年1月1日0点0分0秒的毫秒数
		return time;
	}
	
	public static String getSelectSQLWithPage(String strFullSQL, int iBegin, int iEnd) {
		String strPageSQL = strFullSQL
				+ " limit " + iEnd + "," + iBegin;
		return strPageSQL;
	}

	public static boolean judgeNum(Integer num) {
		if (num != null) {
			return true;
		}
		return false;
	}

	public static boolean judgeNum(Double num) {
		if (num != null && num != 0) {
			return true;
		}
		return false;
	}

	public static boolean judgeNum(BigDecimal num) {
		if (num != null && num.compareTo(BigDecimal.ZERO) != 0) {
			return true;
		}
		return false;
	}

	public static boolean judgeNum(Long num) {
		if (num != null && num != 0) {
			return true;
		}
		return false;
	}

	public static boolean judgeStr(String str) {
		if (str != null && !str.isEmpty()) {
			return true;
		}
		return false;
	}

	
	public static void writeJson(HttpServletResponse response, Object page) throws IOException {
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		// ObjectMapper mapper = new ObjectMapper();
		// String jsonfromList = mapper.writeValueAsString(content);
		String strContent = null;
		Gson gson = new Gson();
		if (page != null) {
			// JSONObject responseJSONObject = JSONObject.fromObject(content);
			// strContent = mapper.writeValueAsString(page);
			strContent = gson.toJson(page);
		} else {
			strContent = "{}";
		}

		byte[] b = strContent.getBytes("utf-8");
		response.setContentLength(b.length);
		response.getOutputStream().write(b);
	}

	
	public static boolean judgeList(List<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}



	public static String sendPostRequest(String strUrl, String data) throws IOException {

		// Build parameter string

		// Send the request
		URL url = new URL(strUrl);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

		// write parameters
		writer.write(data);
		writer.flush();

		// Get the response
		StringBuffer answer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			answer.append(line);
		}
		writer.close();
		reader.close();
		return answer.toString();

	}
	
	public static String getLocalIP() {
        String ip = "";
        try {
            Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                if (!ni.getName().equals("eth0")) {
                    continue;
                } else {
                    Enumeration<?> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = (InetAddress) e2.nextElement();
                        if (ia instanceof Inet6Address)
                            continue;
                        ip = ia.getHostAddress();
                    }
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return ip;
    }

	static public String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if(request.getHeader("Proxy-Client-IP") != null)
			{
				ip = request.getHeader("Proxy-Client-IP");
			}
			else if(request.getHeader("WL-Proxy-Client-IP") != null)
			{
			ip = request.getHeader("WL-Proxy-Client-IP");
			}
			else if(request.getHeader("HTTP_CLIENT_IP") != null)
			{
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			else if(request.getHeader("HTTP_X_FORWARDED_FOR") != null)
			{
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			else if(request.getRemoteAddr() != null)
			{
				ip = request.getRemoteAddr();
			}
		}
		return ip;
	}
	
	/*
	static public LogBaseInfo setLog(HttpServletRequest request,String operateContent,String operateModule)
    {
        LogBaseInfo logBaseInfo = new LogBaseInfo();
        logBaseInfo.setIpAddress(getIp(request));
        HttpSession session = request.getSession();
        logBaseInfo.setUserName(String.valueOf(session.getAttribute("account")));
        logBaseInfo.setOperateContent(operateContent);
        logBaseInfo.setOperateModule(operateModule);
        return logBaseInfo;
    }
	*/
	public static void setLog(String audResource,String audAction) throws Exception
    {
	    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    setLog(request,audResource,audAction);
    }
	
	
    public static void setLog(HttpServletRequest request,String audResource,String audAction) throws Exception
    {
       HostData addr = new HostData();
	   String serverIp=addr.getIp();
       String timestamp = String.valueOf(System.currentTimeMillis());
       String applicCd=request.getContextPath();
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Map<String, String> map = new HashMap<>();
	   HttpSession session = request.getSession();
	   String ip=getIp(request);
	   String account=String.valueOf(session.getAttribute("account"));
	   Random random = new Random();
	   map.put("appKey", RetCode.APPKEY);
	   map.put("timestamp", timestamp);
	   map.put("audUser", account);
	   map.put("audClientIp", ip);
	   map.put("audServerIp",serverIp);
	   map.put("audResource", audResource);
	   map.put("audAction",audAction);
	   map.put("applicCd", applicCd);
	   map.put("audDate", format.format(new Date()));
	   map.put("randomStr", String.valueOf(random.nextInt()));
	   String sign = SignUtil.getSignature(map, RetCode.APPSECRET);
	   map.put("sign", sign);
	   try
	   { 
	     HttpUtil.post("", RetCode.LOGURL.concat(getUrlParamsByMap(map)));
	   } catch (Exception e)
	   {
	       logger.error(e);
	   }
      }

    public static String getUrlParamsByMap(Map<String, String> map)
    {
	  if (map == null)
	  {
	    return "";
	  }
	  StringBuffer sb = new StringBuffer();
	  for (Map.Entry<String, String> entry : map.entrySet())
	  {
	    try
	    {
		sb.append(entry.getKey() + "="
			+ URLEncoder.encode(entry.getValue(), "utf-8"));
		sb.append("&");
	    } catch (Exception e)
	    {
	        logger.error(e);
	    } 
	  }
	  String s = sb.toString();
	  if (s.endsWith("&"))
	  {
	     s = s.substring(0, s.length() - 1);
	  }
	  return s;
    }

	
	public static String erase(String str) {
		return str = str.substring(0, str.length() - 1);
	}
	
	
	 public static void  checkDirs(String parentPath) {  
		 File file = new File(parentPath);
         //判断上传文件的保存目录是否存在
       if(!file.exists() && !file.isDirectory()) {
             System.out.println(parentPath+"目录不存在，需要创建");
             //创建目录
            file.mkdirs();
         }
     }

	public static String toQuotedString(String input) {
		ArrayList<String> quoteList = new ArrayList<String>();
		String[] str = input.split(",");
		for(int i = 0; i < str.length; ++i) {
			String item = str[i].trim();
			quoteList.add("\'" + item + "\'");
		}
		return StringUtils.join(quoteList, ", ");
	}

	public static List<String> QuotedStringToList(String input) {
		List<String> list = new ArrayList<String>();
		String[] str = input.split(",");
		for(int i = 0; i < str.length; ++i) {
			String item = str[i].trim();
			list.add(item);
		}
		return list;
	}

	public static Date nextExcuteTime(String cron) {
		try {
			Date e = new Date();
			CronExpression cronstr = new CronExpression(cron);
			Date next = cronstr.getNextValidTimeAfter(e);
			return next;
		} catch (Exception e) {
			throw new MonitorException(e);
		}
	}

	public static Date nextExcuteTime(Date date,String cron) {
		try {
			date.setTime(date.getTime() + 1);
			CronExpression cronstr = new CronExpression(cron);
			Date next = cronstr.getNextValidTimeAfter(date);
			return next;
		} catch (Exception e) {
			throw new MonitorException(e);
		}
	}
	
	public static String getDecodeScope(){
	    String str= urlConfig.getProperty(RetCode.DBDRIVER);
	    switch(str) {
	        case RetCode.DBDRIVERMYSQL:
	            return "IF(scope=1, 0, 1)";
	        case RetCode.DBDRIVERORACLE:
	            return "decode(scope,1,0,0,1)";
	        default:
	            throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
	    }
	}
	
	public static String getSqlPageString(String sql,LogCondition cond) {
		 String startRowNo = String.valueOf(cond.getStartPage()-1);
	    String str= urlConfig.getProperty(RetCode.DBDRIVER);
	    switch(str) {
	        case RetCode.DBDRIVERMYSQL:
	            return sql + " LIMIT " + startRowNo +","+cond.getPageSize();
	        case RetCode.DBDRIVERORACLE:
	            return "select * from (select row_.*, rownum ob_rownum_  from (" + sql
	                    + ") row_  where rownum <=" + cond.getEndPage() + ") where ob_rownum_ >=" + cond.getStartPage();
	        default:
	            throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
	    }
	}
	
	
	public static String getPageSqlString(String sql,Pagination cond) {
		 String startRowNo = String.valueOf(cond.getStartPage()-1);
	    String str= urlConfig.getProperty(RetCode.DBDRIVER);
	    switch(str) {
	        case RetCode.DBDRIVERMYSQL:
	            return sql + " LIMIT " + startRowNo +","+cond.getPageSize();
	        case RetCode.DBDRIVERORACLE:
	            return "select * from (select row_.*, rownum ob_rownum_  from (" + sql
	                    + ") row_  where rownum <=" + cond.getEndPage() + ") where ob_rownum_ >=" + cond.getStartPage();
	        default:
	            throw new MonitorSimpleException(String.format(RetCode.DBERROR, new Object[]{str}));
	    }
	}

	public static String getDbDriver(DbType code)
	{
		switch(code) {
			case MySql:
				return  RetCode.DBDRIVERMYSQL;
			case Oracle:
				return  RetCode.DBDRIVERORACLE;
			case GreenPlum:
				return  RetCode.DBDRIVERGREENPLUM;
			default:
				throw new MonitorSimpleException(String.format("未知数据库驱动,code[%s]", new Object[]{code}));
		}
	}

	public static String getDbUrl(DbType code, String dbName, String hostIp, String dbPort)
	{
		switch(code) {
			case MySql:
				return  "jdbc:mysql://"+hostIp+":"+dbPort+"/"+dbName+"?useUnicode=true&characterEncoding=utf8";
			case Oracle:
				return "jdbc:oracle:thin:@"+hostIp+":"+ dbPort +":"+dbName;
			case GreenPlum:
				return "jdbc:pivotal:greenplum://"+hostIp+":"+dbPort+";"+"DatabaseName="+dbName;
			default:
				throw new MonitorSimpleException(String.format("未知数据库连接字符串,[%s]", new Object[]{code}));
		}
	}
}
