package gov.kotkov.mikhail.exercise5.config;

import java.io.File;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class RootConfig extends WebMvcConfigurerAdapter {

	private String sep = File.separator;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(sep +"static" + sep + "**")
        .addResourceLocations(sep + "static" + sep);
    }
	
}
