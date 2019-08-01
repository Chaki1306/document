package com.example.document.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Edi on 30.07.2019.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.example.document.repository")
@EnableTransactionManagement
public class SystemConfig {
}
