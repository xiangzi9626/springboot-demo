package com.example.jdbctemplate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 丁祥 QQ 2421341497
 * @since 2022-11-14
 */
@Getter
@Setter
public class Goods {

    private Integer id;

    private String cid;

    private String title;

    private String content;

    private BigDecimal price;

    /**
     * 商品主图
     */
    private String img;
    private Integer stock;
    private Byte status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;
}
