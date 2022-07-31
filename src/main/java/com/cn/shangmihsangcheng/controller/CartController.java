package com.cn.shangmihsangcheng.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cn.shangmihsangcheng.domain.Carts;
import com.cn.shangmihsangcheng.domain.Orders;
import com.cn.shangmihsangcheng.domain.Users;
import com.cn.shangmihsangcheng.service.CartsService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CartController {

    @Resource
    private CartsService cartsService;

    /**
     * 刷新订单总额
     * */

    @GetMapping(value = "/cartTotal")
    @RequiresRoles(value = {"user"})
    public Integer cartTotal(HttpSession session){
        Integer id = ((Users) session.getAttribute("user")).getId();
        Integer integer = cartsService.selectCartTotalPrice(id);
        return integer;
    }

    /**
     * 更新购物车的数量
     * */
    @GetMapping(value = "/cartAllAmount")
    @RequiresRoles(value = {"user"})
    public Integer cartAllAmount(HttpSession session){
        Integer id = ((Users) session.getAttribute("user")).getId();
        return cartsService.selectCartTotalPrice(id);
    }

    @PostMapping(value = "/cartLess")
    @RequiresRoles(value = {"user"})
    public  boolean cartLess(@RequestParam(value = "id") Integer id,@RequestParam(value = "num") Integer num){
        boolean update=false;
        if (num>1){
             update = cartsService.update(new LambdaUpdateWrapper<Carts>().set(Carts::getAmount, num-1).eq(Carts::getId, id));
        }
        if (num-1<=0){
            update=cartsService.deleteById(id)==1?true:false;
        }
        return update;
    }



}
