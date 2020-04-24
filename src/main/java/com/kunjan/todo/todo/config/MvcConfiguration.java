package com.kunjan.todo.todo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static org.springframework.http.MediaType.parseMediaType;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    private static final MediaType APPLICATION_YAML = parseMediaType("application/yaml");

    @Autowired
    @Qualifier("jsonMapper")
    private ObjectMapper jsonMapper;

    @Autowired
    @Qualifier("yamlMapper")
    private ObjectMapper yamlMapper;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Register JSON converter on application/json
        converters.add(new AbstractJackson2HttpMessageConverter(
                jsonMapper, MediaType.APPLICATION_JSON
        ) {});

        //Register YAML converter on application/yaml
        converters.add(new AbstractJackson2HttpMessageConverter(
                yamlMapper, APPLICATION_YAML
        ) {});
    }
}
