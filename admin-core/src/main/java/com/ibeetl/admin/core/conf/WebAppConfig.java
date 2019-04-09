package com.ibeetl.admin.core.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther: xiaohang
 * @Date: 2019-01-22 18:57
 * @Description:
 */

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${localFile.root}")
    private String localFileUrl ;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+localFileUrl+"/images/");
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+localFileUrl+"/image/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
