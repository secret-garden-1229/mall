package com.cn.shangmihsangcheng.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品
 * @TableName goods
 */
@TableName(value ="goods")
@Data
public class Goods implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Object id;

    @TableField(exist = false)
    private Types type;
    @TableField(exist = false)
    private Tops top;

    /**
     * 封面
     */
    private String cover;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String gname;

    /**
     * 介绍
     */
    private String intro;

    /**
     * 规格
     */
    private String spec;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 详情
     */
    private String content;

    /**
     * 分类ID
     */
    private Integer typeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}