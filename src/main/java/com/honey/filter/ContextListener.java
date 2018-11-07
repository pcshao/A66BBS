package com.honey.filter;

import com.honey.HoneyConstants;
import com.honey.util.SqlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/17 9:24
 * Copyright © 2015/8/17 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class ContextListener implements ServletContextListener {

    /**
     * ctx == sevletContext
     */
    private final String host_url = "_host";
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //设值到上下文(前台页面_host取了路径)
        sce.getServletContext().setAttribute(host_url, HoneyConstants.WEB_DOMAIN);

        ServletContext application = sce.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
        SqlUtil.checkTables(applicationContext, logger, "mysql.sql");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(host_url);
    }
}
