package com.example.demo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class IndexController {
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request) {
        //文件超初名称
        String originalFileName = file.getOriginalFilename();
        //文件后缀名称
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        JSONObject json = new JSONObject();
        if (!extension.equals(".jpg") && !extension.equals(".jpeg") && !extension.equals(".png") && !extension.equals(".gif")) {
            json.putOpt("success", false);
            json.putOpt("msg", "只支持jpg png jpeg gif图片");
            return json.toString();
        }
//使用hutool生成随机数
        int random = RandomUtil.randomInt(1000, 9999);
//新文件名
        String newFileName = System.currentTimeMillis() + "" + random + extension;
        //获取项目运行目录
        String realPath = request.getServletContext().getRealPath("/upload");
        //获取文件保存的文件夹
        File folder = new File(realPath);
        //如果文件夹不存在创建
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            file.transferTo(new File(folder, newFileName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload/" + newFileName;
            json.putOpt("success", true);
            json.putOpt("url", url);
        } catch (IOException e) {
            e.printStackTrace();
            json.putOpt("success", false);
            json.putOpt("msg", "上传失败请重试");
        }
        return json.toString();
    }
}
