package com.respodo.commerce.admin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
 
@EnableWebMvc
@PropertySource("classpath:application.properties")
@EnableScheduling
@ComponentScan({"com.respodo.commerce.admin.web","com.respodo.commerce.admin.task"})
public class MvcConfig extends WebMvcConfigurerAdapter {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/bower_components/**").addResourceLocations("/bower_components/");
        registry.addResourceHandler("assets/**").addResourceLocations("resources/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
        registry.addResourceHandler("/view/**").addResourceLocations("/view/");
    }
 
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
 
	 @Bean
     public TilesConfigurer tilesConfigurer() {
         final TilesConfigurer configurer = new TilesConfigurer();
         configurer.setDefinitions(new String[] { "WEB-INF/tiles/tiles.xml" });
         configurer.setValidateDefinitions(true);
         configurer.setCheckRefresh(true);
         return configurer;
     }

     @Bean
     public TilesViewResolver tilesViewResolver() {
         final TilesViewResolver resolver = new TilesViewResolver();
         resolver.setViewClass(TilesView.class);
         resolver.setOrder(0);
         return resolver;
     }
    
    @Bean(name="multipartResolver")
    public CommonsMultipartResolver multipartResolver(){
    	CommonsMultipartResolver resolver=new CommonsMultipartResolver();
    	resolver.setMaxUploadSize(-1);
    	
    	return resolver;
    	
    }
 
 
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }
    
 
}