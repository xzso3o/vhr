package com.xzs.vhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xzs.vhr.mapper")
public class VhrSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(VhrSpringbootApplication.class, args);
    }

}
