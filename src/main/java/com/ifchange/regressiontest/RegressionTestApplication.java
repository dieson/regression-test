package com.ifchange.regressiontest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableCaching
@MapperScan("com.ifchange.regressiontest.dao")
public class RegressionTestApplication {


    public static void main(String[] args) {
        SpringApplication.run(RegressionTestApplication.class, args);
    }
}
