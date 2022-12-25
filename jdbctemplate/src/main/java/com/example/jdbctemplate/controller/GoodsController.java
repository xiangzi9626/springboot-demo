package com.example.jdbctemplate.controller;

import com.example.jdbctemplate.entity.Goods;
import com.example.jdbctemplate.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/insert")
    public String insert(@RequestParam("title") String title, @RequestParam("price") BigDecimal price) {
        Goods goods = new Goods();
        goods.setTitle(title);
        goods.setPrice(price);
        int insert = goodsService.insert(goods);
        System.out.println(insert);
        if (insert > 0) {
            return "插入成功";
        } else {
            return "插入失败请重试";
        }
    }

    @RequestMapping("/delete")
    public String insert(@RequestParam("id") long id) {
        int delete = goodsService.delete(id);
        System.out.println(delete);
        if (delete > 0) {
            return "成功";
        } else {
            return "失败请重试";
        }
    }

    @RequestMapping("/update")
    public String update(@RequestParam("title") String title, @RequestParam("price") BigDecimal price, @RequestParam("id") Integer id) {
        Goods goods = new Goods();
        goods.setTitle(title);
        goods.setPrice(price);
        goods.setId(id);
        int update = goodsService.update(goods);
        System.out.println(update);
        if (update > 0) {
            return "更新成功";
        } else {
            return "失败请重试";
        }
    }

    @RequestMapping("/get")
    public Goods getById(@RequestParam("id") Long id) {
        Goods goods = goodsService.getById(id);
        System.out.println(goods);
        return goods;
    }

    @RequestMapping("/list")
    public List<Goods> getList() {
        List<Goods> goodsList = goodsService.getList();
        System.out.println(goodsList);
        return goodsList;
    }
}
