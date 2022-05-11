package com.xq.tmall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xq.tmall.api.Plant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ImageRecognitionController {
     @GetMapping("/img")
    public String img(){
        return "fore/img";
    }
    @ResponseBody
    @PostMapping("/recognition")
    public String recognition(@RequestParam MultipartFile file,@RequestParam String filePath,HttpSession session){
        String originalFileName = file.getOriginalFilename();
         String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String uploadPath;
        String fileName = UUID.randomUUID() + extension;
          uploadPath = session.getServletContext().getRealPath("/") + "res/images/item/productDetailsPicture/" + fileName;
          //JSONObject object = new JSONObject();
        try {
            file.transferTo(new File(uploadPath));
             //object.put("success", true);
            //object.put("fileName", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            //object.put("success", false);
        }
        Plant plant=new Plant(uploadPath);
        String res=plant.plant();
        Gson gson=new Gson();
        Map<String, List<Map<String,String>>> map=gson.fromJson(res,new HashMap<>().getClass());
        JSONObject object = new JSONObject();
        object.put("fileName",fileName);
        for (int i = 0; i <map.get("result").size(); i++) {
            System.out.print(map.get("result").get(i).get("name"));
            object.put("name"+i,map.get("result").get(i).get("name"));
        }

        return object.toString();
    }
}
