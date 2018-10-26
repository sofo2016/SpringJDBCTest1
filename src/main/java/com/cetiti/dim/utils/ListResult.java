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

import java.util.ArrayList;
import java.util.List;

public class ListResult extends Result {
    private List data = new ArrayList<>();
    public List getRows() {
        return data;
    }
    public void setRows(List<?> rows) {
        this.data = rows;
    }
}
