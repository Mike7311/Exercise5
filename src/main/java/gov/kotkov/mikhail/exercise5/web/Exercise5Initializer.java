package gov.kotkov.mikhail.exercise5.web;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import gov.kotkov.mikhail.exercise5.config.DataConfig;
import gov.kotkov.mikhail.exercise5.config.RootConfig;
import gov.kotkov.mikhail.exercise5.web.config.Exercise5WebConfig;

@Order(1)
public final class Exercise5Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {RootConfig.class, DataConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
    	return new Class<?>[] {Exercise5WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
