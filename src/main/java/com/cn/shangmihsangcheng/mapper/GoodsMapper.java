package com.cn.shangmihsangcheng.mapper;

import com.cn.shangmihsangcheng.domain.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【goods(商品)】的数据库操作Mapper
* @createDate 2022-07-23 16:17:11
* @Entity com.cn.shangmihsangcheng.domain.Goods
*/
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> selectPopularGoods(Integer type);
    List<Goods> goodsgroupBy();
    Goods selectGoodsAndTypeById(Integer id);

    List<Goods> selectAll();
    List<Goods> selectGoodsAdmin(Integer type);
    List<Goods> selectGoodsType();
}




