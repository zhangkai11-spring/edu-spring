package com.zhangkai.service_oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.zhangkai",exclude = DataSourceAutoConfiguration.class)
public class OssAplication {
    public static void main(String[] args) {
        SpringApplication.run(OssAplication.class, args);
    }

}
