package com.example.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.example.providerApi.util.PromotionApiConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@EnableDubboConfiguration
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.example.consumer")
@Import(PromotionApiConfig.class)
public class ConsumerConfig {

}
