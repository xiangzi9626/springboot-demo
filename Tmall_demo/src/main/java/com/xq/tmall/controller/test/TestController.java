package com.xq.tmall.controller.test;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class TestController {
    //获取jar包所在目录
    @GetMapping("/test")
    public void test1(){
        ApplicationHome ah= new ApplicationHome(getClass());
        File file = ah.getSource();
        File file1=new File(file.getParentFile().toString()+"/upload");
        if (!file1.exists()){
            file1.mkdirs();
        }
       // System.out.println(file.getParentFile().toString());
    }
}
