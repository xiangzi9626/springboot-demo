package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ResponseController {
    @RequestMapping("/rep/{id}")
    public String get(@PathVariable("id") String id) {
        return id;
    }

    @RequestMapping("/rep1")
    public String rep1(@RequestBody Map<String, Object> param) {
        return param.get("name").toString();
    }
}
