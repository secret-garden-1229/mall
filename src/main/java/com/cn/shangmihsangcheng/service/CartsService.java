package com.cn.shangmihsangcheng.service;

import com.cn.shangmihsangcheng.domain.Carts;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【carts(购物车)】的数据库操作Service
* @createDate 2022-07-23 16:17:11
*/
public interface CartsService extends IService<Carts> {

    List<Carts> selectCart(Integer userId);
    Integer selectCartTotalPrice(Integer userId);
    List<Carts> selectAmountByUserId( Integer userId);
    Integer deleteById(@Param("id") Integer id);
    List<Carts> selectCartByUserId(Integer userId);
}
