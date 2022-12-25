package com.example.jdbctemplate.dao;

import com.example.jdbctemplate.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GoodsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(Goods goods) {
        int insert = jdbcTemplate.update("insert into goods(title,price) values(?,?)"
                , goods.getTitle(), goods.getPrice());
        return insert;
    }

    public int delete(long id) {
        int delete = jdbcTemplate.update("delete from goods where id=?", id);
        return delete;
    }

    public int update(Goods goods) {
        int update = jdbcTemplate.update("update goods set title=?,price=? where id=?", goods.getTitle(), goods.getPrice(), goods.getId());
        System.out.println(update);
        return update;
    }

    public Goods getById(long id) {
        Goods goods = jdbcTemplate.queryForObject("select * from goods where id=?", new RowMapper<Goods>() {
            @Override
            public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setTitle(rs.getString("title"));
                return goods;
            }
        }, id);
        return goods;
    }

    public List<Goods> getList() {
        List<Goods> goodsList = jdbcTemplate.query("select * from goods", new RowMapper<Goods>() {
            @Override
            public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setTitle(rs.getString("title"));
                return goods;
            }
        });
        return goodsList;
    }
}
