package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RequestController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") String id) {
        String url = "http://localhost/rep/" + id;
        String str = restTemplate.getForObject(url, String.class);
        return str;
    }

    @RequestMapping("/post")
    public String post() {
        Map<String, Object> param = new HashMap<>();
        param.put("AGE", 100);
        param.put("name", "张三");
        String url = "http://localhost/rep1";
        String str = restTemplate.postForObject(url, param, String.class);
        return str;
    }
}
