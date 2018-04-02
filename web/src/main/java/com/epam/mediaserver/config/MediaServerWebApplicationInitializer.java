package com.epam.mediaserver.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class MediaServerWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();

        applicationContext.register(WebConfig.class, /*SecurityConfig.class,*/ BeanConfig.class);
        applicationContext.setServletContext(servletContext);

        DispatcherServlet servlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
