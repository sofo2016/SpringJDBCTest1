/*
 * File Name: SSOLoginFilter.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2017-3-16
 * Modifier: Wuwuhao
 * Modify Date: 2017-3-16
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dim.utils;

import org.jasig.cas.client.authentication.AttributePrincipal;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 
 * @author Wuwuhao
 * @version DSPlite V0.2.0, 2017-3-16
 * @see
 * @since DSPlite V0.2.0
 */

public class SSOLoginFilter implements Filter
{

    @Override
    public void destroy()
    {// default implementation ignored
    }
    final String accountStr = "account";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (null == session.getAttribute(accountStr))
        {
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            session.setAttribute(accountStr, name);
            AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
            if (null != principal)
            {
                Map<String, Object> attributes = principal.getAttributes();
                String accounts = (String) attributes.get(accountStr);
                if (null != accounts)
                {
                	accounts = accounts.substring(1, accounts.length() - 1);
                    session.setAttribute(accountStr, accounts);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    @Override
    public void init(FilterConfig config) throws ServletException
    {// default implementation ignored
    }

}
