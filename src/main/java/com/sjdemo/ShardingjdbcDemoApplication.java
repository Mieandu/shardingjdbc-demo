package com.sjdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sjdemo.mapper")

public class ShardingjdbcDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingjdbcDemoApplication.class, args);
    }

}
