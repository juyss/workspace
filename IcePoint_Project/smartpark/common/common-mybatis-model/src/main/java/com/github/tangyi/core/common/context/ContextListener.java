package com.github.tangyi.core.common.context;

import com.github.tangyi.core.common.util.LogUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ContextListener
 * servlet上下文监听器
 *
 * @author Galen
 * @since 2016/5/30
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        ApplicationContext.setServletContext(servletContext);
        ApplicationContext.setApplicationContext(WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContext));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LogUtils.info("Context destoryed");
    }

}
