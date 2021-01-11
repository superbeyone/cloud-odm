package com.tdt.cloud.odm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Mr.superbeyone
 * @project cloud-odm
 * @className NacosConfigApp
 * @description
 * @date 2020-12-10 17:22
 **/

@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApp.class, args);
    }
}
