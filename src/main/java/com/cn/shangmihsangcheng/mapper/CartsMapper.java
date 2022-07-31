package com.cn.shangmihsangcheng.mapper;
import org.apache.ibatis.annotations.Param;

import com.cn.shangmihsangcheng.domain.Carts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【carts(购物车)】的数据库操作Mapper
* @createDate 2022-07-23 16:17:11
* @Entity com.cn.shangmihsangcheng.domain.Carts
*/
public interface CartsMapper extends BaseMapper<Carts> {

    List<Carts> selectCart(Integer userId);

    Integer selectCartTotalPrice(Integer userId);

    List<Carts> selectAmountByUserId(@Param("userId") Integer userId);

    int deleteById(@Param("id") Integer id);

    List<Carts> selectCartByUserId(Integer userId);

}




