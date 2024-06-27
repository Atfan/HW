package org.company.ticketonline2.configuration;

import org.company.ticketonline2.service.converter.StringToPlaceDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private StringToPlaceDTOConverter stringToPlaceDTOConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToPlaceDTOConverter);
    }
}
