package com.fosu.shop.business.configurer;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 本地文件映射到url
 * @author Administrator
 */
@Configuration
class MyWebMvcConfigurer implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
       // registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的doc目录下，访问路径如：http://localhost:9002/doc/one.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        registry.addResourceHandler("/doc/**").addResourceLocations("file:D:/doc/");
        }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
        }
}

