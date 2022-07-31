package com.cn.shangmihsangcheng.mapper;

import com.cn.shangmihsangcheng.domain.Items;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【items(订单项)】的数据库操作Mapper
* @createDate 2022-07-23 16:17:11
* @Entity com.cn.shangmihsangcheng.domain.Items
*/
public interface ItemsMapper extends BaseMapper<Items> {
    List<Items> selectItemsById(Integer userId);
}




