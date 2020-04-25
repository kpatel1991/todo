package com.kunjan.todo.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Configures a Clock instance based on the system
 * clock to UTC timezone. To get static time to
 * tests, override this configuration with
 * Clock.fixed(...).
 */
@Configuration
public class ClockConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
