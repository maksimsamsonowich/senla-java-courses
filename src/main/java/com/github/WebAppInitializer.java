package com.github;

import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.configs.web.WebConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ApplicationConfig.class, DatabaseConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @NotNull
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/senla/*" };
    }

}
