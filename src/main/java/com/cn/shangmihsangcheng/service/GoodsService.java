package com.cn.shangmihsangcheng.service;

import com.cn.shangmihsangcheng.domain.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【goods(商品)】的数据库操作Service
* @createDate 2022-07-23 16:17:11
*/
public interface GoodsService extends IService<Goods> {
    List<Goods> selectPopularGoods(Integer type);
     List<Goods>goodsgroupBy();
     Goods selectGoodsAndTypeById(Integer id);
    List<Goods> selectAll();
    List<Goods> selectGoodsAdmin(Integer type);
    List<Goods> selectGoodsType();
}
