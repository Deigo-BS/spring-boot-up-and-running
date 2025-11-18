package com.diego.jpaoracle.cofig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.diego.jpaoracle.proxies")
public class ProjectConfig {
}
