package com.cn.shangmihsangcheng.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 分类
 * @TableName types
 */
@TableName(value ="types")
@Data
public class Types implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String tname;

    /**
     * 排序号 (从小到大)
     */
    private Integer num;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}