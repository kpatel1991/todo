package com.kunjan.todo.todo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Configure two dedicated Jackson ObjectMapper instances for JSON & YAML
 * that can be injected via
 *
 * \@Qualifier("yamlMapper")
 *
 * or
 *
 * \@Qualifier("jsonMapper")
 *
 * BEWARE:
 *
 * THE JSON MAPPER REPLACES THE AUTO CONFIGURED SPRING BOOT VERSION, SO BEWARE
 * THAT THE SPRING.JACKSON.XXX CONFIGURATION PROPERTY WONT WORK.
 */

@Configuration
public class JacksonConfiguration {

    @Bean
    @Qualifier("yamlMapper")
    public ObjectMapper yamlMapper(){
        return configure(new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
        .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)));
    }

    @Bean
    @Primary
    @Qualifier("jsonMapper")
    public ObjectMapper jsonMapper(){
        return configure(new ObjectMapper());
    }

    private ObjectMapper configure(ObjectMapper objectMapper) {
        return objectMapper
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
}
