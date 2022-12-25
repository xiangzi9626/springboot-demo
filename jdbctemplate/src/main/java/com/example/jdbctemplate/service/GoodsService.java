package com.example.jdbctemplate.service;

import com.example.jdbctemplate.dao.GoodsDao;
import com.example.jdbctemplate.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public int insert(Goods goods) {
        int insert = goodsDao.insert(goods);
        return insert;
    }

    public int delete(long id) {
        int delete = goodsDao.delete(id);
        return delete;
    }

    public int update(Goods goods) {
        int update = goodsDao.update(goods);
        return update;
    }

    public Goods getById(long id) {
        Goods goods = goodsDao.getById(id);
        return goods;
    }

    public List<Goods> getList() {
        List<Goods> goodsList = goodsDao.getList();
        return goodsList;
    }
}
