package com.cn.shangmihsangcheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmihsangcheng.domain.Goods;
import com.cn.shangmihsangcheng.service.GoodsService;
import com.cn.shangmihsangcheng.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【goods(商品)】的数据库操作Service实现
* @createDate 2022-07-23 16:17:11
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> selectPopularGoods(Integer type) {
        return goodsMapper.selectPopularGoods(type);
    }

    @Override
    public List<Goods> goodsgroupBy() {
        return goodsMapper.goodsgroupBy();
    }

    @Override
    public Goods selectGoodsAndTypeById(Integer id) {
        return goodsMapper.selectGoodsAndTypeById(id);
    }

    @Override
    public List<Goods> selectAll() {
        return goodsMapper.selectAll();
    }

    @Override
    public List<Goods> selectGoodsAdmin(Integer type) {
        return goodsMapper.selectGoodsAdmin( type);
    }

    @Override
    public List<Goods> selectGoodsType() {
        return goodsMapper.selectGoodsType();
    }
}




