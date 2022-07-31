package com.cn.shangmihsangcheng.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cn.shangmihsangcheng.domain.Carts;
import com.cn.shangmihsangcheng.domain.Items;
import com.cn.shangmihsangcheng.domain.Orders;
import com.cn.shangmihsangcheng.domain.Users;
import com.cn.shangmihsangcheng.service.CartsService;
import com.cn.shangmihsangcheng.service.ItemsService;
import com.cn.shangmihsangcheng.service.OrdersService;
import com.cn.shangmihsangcheng.utils.KeyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class OrderController {

    @Resource
    private ItemsService itemsService;

    @Resource
    private CartsService cartsService;

    @Resource
    private OrdersService ordersService;

    @GetMapping(value = "/orderSave")
    @RequiresRoles(value = {"user"})
    public ModelAndView orderSave(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        Users user = (Users) session.getAttribute("user");
        List<Carts> items = cartsService.selectCart(user.getId());
        Double total=0D;
        for (Carts item : items) {
            total= Double.valueOf(item.getAmount()*item.getGood().getPrice())+total;
        }
        Orders orders=new Orders();


        modelAndView.addObject("order",items);
        modelAndView.addObject("total",total);
        modelAndView.addObject("id",user.getId());
        modelAndView.setViewName("/index/pay.jsp");
        return modelAndView;
    }



    @PostMapping(value = "/orderPay")
    @RequiresRoles(value = {"user"})
    public ModelAndView orderPay(HttpSession session,
                                 @RequestParam(value = "paytype") Integer paytype,
                                 @RequestParam(value = "name")String name,
                                 @RequestParam(value = "phone")String phone,
                                 @RequestParam(value = "address")String address,
                                 @RequestParam(value = "id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Users user = (Users) session.getAttribute("user");
        Items items1=null;
        List<Carts> items = cartsService.selectCart(user.getId());
        Integer ids=Integer.parseInt(KeyUtils.getUniqueKey().substring(0,10));
        Integer total=0;
        int a=0;
        for (Carts item : items) {
            total= item.getAmount()*item.getGood().getPrice()+total;
            a=a+item.getAmount();
            items1=new Items();
            items1.setAmount(item.getAmount());
            items1.setGoodId(item.getGoodId());
            items1.setPrice(item.getGood().getPrice());
            items1.setOrderId(ids);
            itemsService.saveOrUpdate(items1);
        }
        Orders orders=new Orders();
        orders.setName(user.getName());
        orders.setStatus(2);
        orders.setId(ids);
        orders.setTotal(total);
        orders.setAmount(a);
        orders.setName(user.getName());
        orders.setUserId(id);
        orders.setPaytype(paytype);
        orders.setName(name);
        orders.setPhone(phone);
        orders.setAddress(address);
        orders.setSystime(new Date());
        boolean flag = ordersService.saveOrUpdate(orders);

        if (flag){
            modelAndView.setViewName("/index/payok.jsp");
        }else {
            modelAndView.addObject("msg","支付失败，请稍后再试");
            modelAndView.setViewName("/index/error.jsp");
        }
        return modelAndView;
    }

    @GetMapping(value = "/order")
    public ModelAndView selectOrder(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        ModelAndView modelAndView=new ModelAndView();
        List<Orders> orders = ordersService.selectNewOrderById(user.getId());
        System.out.println(orders);
        modelAndView.addObject("orderList",orders);
        modelAndView.setViewName("/index/order.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/orderList")
    @RequiresRoles(value = {"user"})
    public ModelAndView orderList(@RequestParam(value = "status",defaultValue = "",required = false) Integer status,
                                  @RequestParam(value = "pageSize",defaultValue = "4")Integer pageSize,
                                  @RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo){
        ModelAndView modelAndView=new ModelAndView();
        PageHelper.startPage(pageNo,pageSize);
        List<Orders> selectorderinfo = ordersService.selectorderinfo(status);
        PageInfo<Orders> pageInfo =new PageInfo<>(selectorderinfo);
        List<Integer> index=new ArrayList<>();
        for (int i=pageInfo.getPageNum();i<=pageInfo.getPages();i++){
            index.add(i);
        }
        modelAndView.addObject("pages",index);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("status",status);
        modelAndView.addObject("orderList",selectorderinfo);
        modelAndView.setViewName("/admin/order_list.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/orderUpdate")
    @RequiresRoles(value = {"user"})
    public ModelAndView orderUpdate(Orders orders){
        ordersService.updateById(orders);
        ModelAndView modelAndView=new ModelAndView();
        PageHelper.startPage(1,4);
        List<Orders> selectorderinfo = ordersService.selectorderinfo(orders.getStatus());
        PageInfo<Orders> pageInfo =new PageInfo<>(selectorderinfo);
        List<Integer> index=new ArrayList<>();
        for (int i=pageInfo.getPageNum();i<=pageInfo.getPages();i++){
            index.add(i);
        }
        modelAndView.addObject("pages",index);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("status",orders.getStatus());
        modelAndView.addObject("orderList",selectorderinfo);
        modelAndView.setViewName("/admin/order_list.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/orderDelete")
    @RequiresRoles(value = {"user"})
    public ModelAndView orderDelete(Integer id){
        boolean remove = ordersService.remove(new LambdaUpdateWrapper<Orders>().eq(Orders::getId, id));
        ModelAndView modelAndView=new ModelAndView();
        PageHelper.startPage(1,4);
        Integer status=null;
        List<Orders> selectorderinfo = ordersService.selectorderinfo(status);
        PageInfo<Orders> pageInfo =new PageInfo<>(selectorderinfo);
        List<Integer> index=new ArrayList<>();
        for (int i=pageInfo.getPageNum();i<=pageInfo.getPages();i++){
            index.add(i);
        }
        modelAndView.addObject("pages",index);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("status",status);
        modelAndView.addObject("orderList",selectorderinfo);
        modelAndView.setViewName("/admin/order_list.jsp");
        return modelAndView;
    }

}
