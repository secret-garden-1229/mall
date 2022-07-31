package com.cn.shangmihsangcheng.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.cn.shangmihsangcheng.domain.*;

import com.cn.shangmihsangcheng.service.*;

import com.cn.shangmihsangcheng.vo.ReturnData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SettingController {

    @Resource
    private TypesService typesService;
    @Resource
    private UsersService usersService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private CartsService cartsService;

    @Resource
    private OrdersService ordersService;

    @GetMapping(value = "/")
    public ModelAndView toIndex(){
        PageHelper.startPage(1,6,"sales asc");
        ModelAndView modelAndView=new ModelAndView();
        List<Goods> goods = goodsService.selectPopularGoods(2);
        List<Goods> list = goodsService.selectPopularGoods(1);
        PageInfo pageInfo=new PageInfo(goods);
        PageInfo pageInfo1=new PageInfo(list);
        QueryWrapper<Types>    queryWrapper = new QueryWrapper().select("tname", "id");
        /**
         * 获取所有的类目
         * */
        List<Types> type = typesService.list(queryWrapper).stream().distinct().collect(Collectors.toList());
        /**
         * 对商品进行分组
         * */
        List<Goods> goods1 = goodsService.goodsgroupBy();
        /**
         * 容器
         * */
        List<ReturnData> list1=new ArrayList<>();

        type.stream().filter(x->{
            ReturnData returnData=new ReturnData();
            returnData.setType(x);
            List<Goods> goodsList=new ArrayList<>();
            goods1.forEach(e->{
                if (e.getTypeId() == x.getId()){
                   goodsList.add(e);
               }
            });
            returnData.setGoodList(goodsList);
            list1.add(returnData);
            return false;
        }).forEach(System.out::println);
        modelAndView.addObject("dataList",list1);
        modelAndView.addObject("todayLists",pageInfo.getList());
        modelAndView.addObject("hotList",pageInfo1.getList());
        modelAndView.addObject("flag",1);
        modelAndView.setViewName("/index/index.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ModelAndView login(HttpSession session, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        int a=0;
        try {
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){
            a++;

        }catch (IncorrectCredentialsException e){
            a++;

        }
        if (a==0){
            session.setAttribute("user",SecurityUtils.getSubject().getPrincipal());
            PageHelper.startPage(1,6,"sales asc");
            ModelAndView modelAndView=new ModelAndView();
            List<Goods> goods = goodsService.selectPopularGoods(2);
            List<Goods> list = goodsService.selectPopularGoods(1);
            PageInfo pageInfo=new PageInfo(goods);
            PageInfo pageInfo1=new PageInfo(list);
            QueryWrapper<Types>    queryWrapper = new QueryWrapper().select("tname", "id");
            /**
             * 获取所有的类目
             * */
            List<Types> type = typesService.list(queryWrapper).stream().distinct().collect(Collectors.toList());
            /**
             * 对商品进行分组
             * */
            List<Goods> goods1 = goodsService.goodsgroupBy();
            /**
             * 容器
             * */
            List<ReturnData> list1=new ArrayList<>();

            type.stream().filter(x->{
                ReturnData returnData=new ReturnData();
                returnData.setType(x);
                List<Goods> goodsList=new ArrayList<>();
                goods1.forEach(e->{
                    if (e.getTypeId() == x.getId()){
                        goodsList.add(e);
                    }
                });
                returnData.setGoodList(goodsList);
                list1.add(returnData);
                return false;
            }).forEach(System.out::println);
            modelAndView.addObject("dataList",list1);
            modelAndView.addObject("todayLists",pageInfo.getList());
            modelAndView.addObject("hotList",pageInfo1.getList());
            modelAndView.addObject("flag",1);
            modelAndView.setViewName("/index/index.jsp");
            return modelAndView;
        }else {
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("/index/index.jsp");
            modelAndView.addObject("msg","请确定账号密码正确");
            return modelAndView;
        }

    }

    @GetMapping(value = "/tocart")
    @RequiresRoles(value = {"user"})
    public ModelAndView toCarts(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        List<Carts> list = cartsService.selectCartByUserId(user.getId());

        Integer cartTotal=0;

        for (Carts carts : list) {
            cartTotal=cartTotal+carts.getGood().getPrice()*carts.getAmount();
        }

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("cartTotal",cartTotal);
        modelAndView.addObject("cartList",list);

        modelAndView.setViewName("/index/cart.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/toOrder")
    @RequiresRoles(value = {"user"})
    public ModelAndView toOrder(HttpSession session){
        ModelAndView modelAndView =new ModelAndView();
        Users user = (Users) session.getAttribute("user");
        List<Orders> orderList = ordersService.selectOrderById(user.getId());
        modelAndView.addObject("orderList",orderList);
        modelAndView.setViewName("/index/order.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/toAddress")
    public  ModelAndView toAddress(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/index/address.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/toPassword")
    public ModelAndView toPassword(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/index/password.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/logout")
    @RequiresRoles(value = {"user"})
    public ModelAndView logout(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        PageHelper.startPage(1,6,"sales asc");
        ModelAndView modelAndView=new ModelAndView();
        List<Goods> goods = goodsService.selectPopularGoods(2);
        List<Goods> list = goodsService.selectPopularGoods(1);
        PageInfo pageInfo=new PageInfo(goods);
        PageInfo pageInfo1=new PageInfo(list);
        QueryWrapper<Types>    queryWrapper = new QueryWrapper().select("tname", "id");
        /**
         * 获取所有的类目
         * */
        List<Types> type = typesService.list(queryWrapper).stream().distinct().collect(Collectors.toList());
        /**
         * 对商品进行分组
         * */
        List<Goods> goods1 = goodsService.goodsgroupBy();
        /**
         * 容器
         * */
        List<ReturnData> list1=new ArrayList<>();

        type.stream().filter(x->{
            ReturnData returnData=new ReturnData();
            returnData.setType(x);
            List<Goods> goodsList=new ArrayList<>();
            goods1.forEach(e->{
                if (e.getTypeId() == x.getId()){
                    goodsList.add(e);
                }
            });
            returnData.setGoodList(goodsList);
            list1.add(returnData);
            return false;
        }).forEach(System.out::println);
        modelAndView.addObject("dataList",list1);
        modelAndView.addObject("todayLists",pageInfo.getList());
        modelAndView.addObject("hotList",pageInfo1.getList());
        modelAndView.addObject("flag",1);
        modelAndView.setViewName("/index/index.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/administratorLogin")
    public  ModelAndView administratorLogin(HttpSession session,@RequestParam(value = "username") String username,@RequestParam(value = "password")String password){
        Subject subject = SecurityUtils.getSubject();
        ModelAndView modelAndView=new ModelAndView();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        int a=0;
        try {
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){
            a++;
            modelAndView.addObject("msg","账号不存在");
            modelAndView.setViewName("/admin.jsp");
            return modelAndView;
        }catch (IncorrectCredentialsException e){
            a++;
            modelAndView.addObject("msg","密码不匹配");
            modelAndView.setViewName("/admin.jsp");
            return modelAndView;
        }

        session.setAttribute("administratorUser",SecurityUtils.getSubject().getPrincipal());
        modelAndView.setViewName("admin/index.jsp");
        return modelAndView;
    }

    /**
     * 今日推荐
     * */
    @GetMapping(value = "/today")
    public ModelAndView toDay(@RequestParam(value = "pageNo",defaultValue = "1",required = false) Integer pageNo,@RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize){
        ModelAndView modelAndView=new ModelAndView();
        PageHelper.startPage(pageNo,pageSize,"sales asc");
        List<Goods> list = goodsService.selectPopularGoods(2);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        modelAndView.addObject("todayLists",pageInfo.getList());
        modelAndView.setViewName("/index/today.jsp");
        modelAndView.addObject("flag",2);
        return modelAndView;
    }

    /**
     * 热销排行
     * */
    @GetMapping(value = "/hot")
    public ModelAndView toHot(@RequestParam(value = "pageNo",defaultValue = "1",required = false) Integer pageNo,@RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize){
        ModelAndView modelAndView=new ModelAndView();
        PageHelper.startPage(pageNo,pageSize,"sales asc");
        List<Goods> list = goodsService.selectPopularGoods(1);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        modelAndView.addObject("hotList",pageInfo.getList());
        modelAndView.setViewName("/index/hot.jsp");
        modelAndView.addObject("flag",3);
        return modelAndView;
    }


    /**
     * search
     *     模糊查询商品
     * */

    @GetMapping(value = "/search")
    public ModelAndView search(String name){
        ModelAndView modelAndView=new ModelAndView();
        List<Goods> list = goodsService.list(new LambdaQueryWrapper<Goods>().like(Goods::getGname, name));
        modelAndView.addObject("todayLists",list);
        modelAndView.addObject("flag",5);
        modelAndView.setViewName("/index/index.jsp");
        return modelAndView;
    }

    /**
     * 新品上市
     * */
    @GetMapping("/newGoods")
    public ModelAndView newGoods(){
        ModelAndView modelAndView=new ModelAndView();
        List<Goods> list = goodsService.selectAll();
        modelAndView.addObject("hotList",list);
        modelAndView.setViewName("/index/newGoods.jsp");
        return modelAndView;
    }


    /**
     * 实现注册功能
     * */
    @PostMapping(value = "/register")
    public ModelAndView register( Users users){
        ModelAndView modelAndView=new ModelAndView();
        boolean b = usersService.saveOrUpdate(users);
        if (b){
            modelAndView.setViewName("/index/login.jsp");
        }else {
            modelAndView.addObject("msg","系统繁忙请稍后重试");
            modelAndView.setViewName("/index/register.jsp.jsp");
        }
        return modelAndView;
    }


    /**
     * 加入购物车
     * */
    @PostMapping(value = "/cartBuy")
    @RequiresRoles(value = {"user"})
    public boolean cartBuy(Integer goodId,HttpSession session){
         Carts carts=new Carts();
        Users user = (Users) session.getAttribute("user");
            Integer id=user.getId();
         carts.setAmount(1);
         carts.setUserId(id);
         carts.setGoodId(goodId);
        boolean b = cartsService.saveOrUpdate(carts);
       return b;
    }

    /**
     *添加商品数量
     * */
    @PostMapping(value = "/cartAdd")
    @RequiresRoles(value = {"user"})
    public boolean cartAdd(@RequestParam(value = "id") Integer id,@RequestParam(value = "num") Integer num){
        boolean update = cartsService.update(new LambdaUpdateWrapper<Carts>().set(Carts::getAmount, num+1).eq(Carts::getId, id));
        return update;
    }



}
