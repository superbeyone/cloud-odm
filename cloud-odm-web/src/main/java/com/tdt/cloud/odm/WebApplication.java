package com.tdt.cloud.odm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author Mr.superbeyone
 * @project cloud-odm
 * @className Application
 * @description
 * @date 2020-12-10 16:49
 **/

@RefreshScope
@SpringBootApplication
@EnableDiscoveryClient
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
