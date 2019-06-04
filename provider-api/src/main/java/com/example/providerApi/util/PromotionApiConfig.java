package com.example.providerApi.util;

import com.example.providerApi.point.PromotionBrick;
import com.example.providerApi.point.PromotionBricks;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Configuration
@ComponentScan
public class PromotionApiConfig {


    @Autowired
    private ApplicationContext applicationContext;

    @Bean(name = "promotion-bricks-bean-processor")
    public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (!(bean instanceof PromotionBricks)) {
                    return bean;
                }
                PromotionBricks promotionBricks = (PromotionBricks) bean;
                Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(PromotionBrick.class);
                for (Object brick : beanMap.values()) {
                    PromotionBrick brickAnn = brick.getClass().getAnnotation(PromotionBrick.class);
                    promotionBricks.register(brickAnn.key(), brick);
                }
                return promotionBricks;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }
        };
    }
}
