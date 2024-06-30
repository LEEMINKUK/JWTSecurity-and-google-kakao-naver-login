//package com.soop.jwtsecurity.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/**")
//                .exposedHeaders("Set-Cookie", "Authorization")
//                .allowedOrigins("http://localhost:3000")
//                .allowedHeaders(
//                        "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
//                        "Content-Type", "Authorization", "X-Requested-With", "Access-Token", "Refresh-Token")
//                .allowCredentials(true)
//                .allowedMethods("GET", "PUT", "POST", "DELETE");
//    }
//}
