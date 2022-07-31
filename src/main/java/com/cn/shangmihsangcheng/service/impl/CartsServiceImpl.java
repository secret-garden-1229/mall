package com.cn.shangmihsangcheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmihsangcheng.domain.Carts;
import com.cn.shangmihsangcheng.service.CartsService;
import com.cn.shangmihsangcheng.mapper.CartsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【carts(购物车)】的数据库操作Service实现
* @createDate 2022-07-23 16:17:11
*/
@Service
public class CartsServiceImpl extends ServiceImpl<CartsMapper, Carts>
    implements CartsService{

    @Resource
    private  CartsMapper cartsMapper;

    @Override
    public List<Carts> selectCart(Integer userId) {
        return cartsMapper.selectCart(userId);
    }

    @Override
    public Integer selectCartTotalPrice(Integer userId) {
        return cartsMapper.selectCartTotalPrice(userId);
    }

    public List<Carts> selectAmountByUserId( Integer userId){
        return cartsMapper.selectAmountByUserId(userId);
    }

    @Override
    public Integer deleteById(Integer id) {
        return cartsMapper.deleteById(id);
    }


    @Override
    public List<Carts> selectCartByUserId(Integer userId) {
        return cartsMapper.selectCartByUserId(userId);
    }
}




