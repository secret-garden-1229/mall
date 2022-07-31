package com.cn.shangmihsangcheng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.cn.shangmihsangcheng.mapper")
@EnableTransactionManagement
public class ShangmihsangchengApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShangmihsangchengApplication.class, args);
    }

}
