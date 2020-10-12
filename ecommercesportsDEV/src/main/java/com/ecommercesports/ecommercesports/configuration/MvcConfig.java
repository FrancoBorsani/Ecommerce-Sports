package com.ecommercesports.ecommercesports.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{  //cofiguracion para definir la ruta/directorio donde se guardaran las imagenes (C://Ecommerce-Sports UsersImg)

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry.addResourceHandler("/Ecommerce-Sports UsersImg/**")
		.addResourceLocations("file:/C:/Ecommerce-Sports UsersImg/");
	}
	
}
