package com.github.configs;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext
                = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(ApplicationConfig.class);

        DispatcherServlet dispatcherServlet
                = new DispatcherServlet(annotationConfigWebApplicationContext);

        ServletRegistration.Dynamic registration =
                servletContext.addServlet("dispatcherServlet", dispatcherServlet);

        registration.setLoadOnStartup(1);

        registration.addMapping("/senla/*");
    }

}
