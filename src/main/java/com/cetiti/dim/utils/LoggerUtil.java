/*
 * File Name: LoggerUtil.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Geyangyang
 * Create Date: 2014-6-19

 * Modifier: Geyangyang
 * Modify Date: 2014-6-19
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dim.utils;

import com.cetiti.dim.common.ProjectPathUtil;
import org.apache.log4j.xml.DOMConfigurator;


/**
 * Logger util.
 * 
 * @author Geyangyang
 * @version DSP V1.0.0, 2014-6-19
 * @see
 * @since DSP V1.0.0
 */

public class LoggerUtil
{
    private static final String LOG4J_CONFIG_PATH = "config/log4j.xml";

    public static void initLog4jConfig()
    {
        DOMConfigurator.configureAndWatch(ProjectPathUtil.getProjectPath()+LOG4J_CONFIG_PATH);
    }
}
