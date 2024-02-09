package com.example.personneservice.controller;

import com.example.personneservice.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/config")
public class TestConfigController {
  /*  @Value("${spring.global.a}")
    private int a;
    @Value("${spring.global.b}")
    private int b;

    @Value("${spring.test.a}")
    private String aa;

    @Autowired
    private GlobalConfig globalConfig;

    @GetMapping("/test2")
    public Map<String, Integer> getConfig(){
        return Map.of("a", a, "b", b);
    }

    @GetMapping("/test1")
    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }*/
}
