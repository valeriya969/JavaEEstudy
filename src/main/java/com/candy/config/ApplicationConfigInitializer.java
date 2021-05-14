package com.candy.config;

import com.candy.servlet.JavaConfigServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class ApplicationConfigInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext ctx) throws ServletException {
        JavaConfigServlet servlet = new JavaConfigServlet();
        ServletRegistration.Dynamic servletConfig = ctx.addServlet("JavaConfigServlet", servlet);
        servletConfig.addMapping("/java");
    }
}
