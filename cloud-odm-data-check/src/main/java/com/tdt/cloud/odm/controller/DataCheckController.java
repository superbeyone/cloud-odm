package com.tdt.cloud.odm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Mr.superbeyone
 * @project cloud-odm
 * @className DataCheckController
 * @description
 * @date 2020-12-10 16:28
 **/
@RestController
@RequestMapping("/data/check")
public class DataCheckController {


    @GetMapping
    public String getStr() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
