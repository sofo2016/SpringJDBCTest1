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

public class ActionResult {
	private String message;
	private int retCode = RetCode.FAILURE;
	Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message, int retCode) {
		message += ",返回码:" + retCode;
		this.message = message;
		this.retCode = retCode;
	}

}
