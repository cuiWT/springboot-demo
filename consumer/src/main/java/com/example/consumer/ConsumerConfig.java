package com.example.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableDubboConfiguration
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.example.consumer")
public class ConsumerConfig {

}
