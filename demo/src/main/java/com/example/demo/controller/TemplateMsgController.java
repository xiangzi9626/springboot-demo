package com.example.demo.controller;

import com.example.demo.weixin.mp.TemplateMsg;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class TemplateMsgController {
    @RequestMapping("/send")
    @ResponseBody
    public String send(){
        //获取模板消息列表
        String res= TemplateMsg.getTemplateList();
        Map<String, List<Map<String,Object>>> mp=new Gson().fromJson(res,Map.class);
        Object id=mp.get("template_list").get(0).get("template_id");
        //发送模板消息
        String send=TemplateMsg.sendTemplateMsg("ocMvMwcxhS8rGqIZuPkBXXDsdAks",id+"","http://www.baidu.com","","","value","#000","value1","#000","value2","#000","value3","#000","value4","#000");
        System.out.print(send);
        return "success";
    }
}
