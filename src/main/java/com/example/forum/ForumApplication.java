package com.example.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootApplication
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

}

/**
 * // For serving angular app (moved to different repo)
 *
 * @Configuration class MvcConfiguration implements WebMvcConfigurer {
 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
 * registry.addResourceHandler("/**")
 * .addResourceLocations("classpath:/public/")
 * .resourceChain(true)
 * .addResolver(new PathResourceResolver() {
 * @Override protected Resource getResource(String resourcePath, Resource location) throws IOException {
 * Resource requestedResource = location.createRelative(resourcePath);
 * <p>
 * return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
 * : new ClassPathResource("/public/index.html");
 * }
 * });
 * }
 * }
 */