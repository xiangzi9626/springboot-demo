package com.example.demo.controller;

import com.example.demo.weixin.mp.Account;
import com.example.demo.weixin.mp.Menu;
import com.example.demo.weixin.mp.TemplateMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class IndexController {
    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "hello";
    }
    @RequestMapping("/test")
    //@ResponseBody
    public String test() throws UnsupportedEncodingException {
        String result= Account.showQrCode(Account.createForeverTicket(100));
        System.out.print(result);
         return "redirect:"+result;
    }
}
