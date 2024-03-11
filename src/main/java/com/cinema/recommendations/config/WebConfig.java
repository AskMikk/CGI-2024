package com.cinema.recommendations.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Teadsin, kuidas seda kohapeal teha, kuid polnud varem üldist konfiguratsiooni teinud
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // https://stackoverflow.com/questions/34801351/how-to-configure-a-default-restcontroller-uri-prefix-for-all-controllers
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api", c -> true);
    }

    // https://stackoverflow.com/questions/35091524/spring-cors-no-access-control-allow-origin-header-is-present
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
