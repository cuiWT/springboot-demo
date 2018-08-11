package com.example.consumerTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.example.consumer.controller")
public class ConsumerTestConfig {

    @Test
    public void test () throws IOException {
        System.out.println("test code is running");
        Assert.assertEquals( 1L, 1L);
    }
}
