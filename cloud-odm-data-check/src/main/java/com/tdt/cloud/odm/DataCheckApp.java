package com.tdt.cloud.odm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Mr.superbeyone
 * @project cloud-odm
 * @className DataCheckApp
 * @description
 * @date 2020-12-10 16:25
 **/

@EnableDiscoveryClient
@SpringBootApplication
public class DataCheckApp {

    public static void main(String[] args) {
        SpringApplication.run(DataCheckApp.class, args);
    }
}
