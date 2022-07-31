package com.cn.shangmihsangcheng.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cn.shangmihsangcheng.domain.Goods;
import com.cn.shangmihsangcheng.domain.Types;
import com.cn.shangmihsangcheng.service.GoodsService;
import com.cn.shangmihsangcheng.service.TypesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.ArrayStack;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private TypesService typesService;


    @GetMapping(value = "/detail")
    public ModelAndView detail(Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Goods goods = goodsService.selectGoodsAndTypeById(id);
        modelAndView.addObject("good",goods);
        modelAndView.setViewName("/index/detail.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/goodAdd")
    @RequiresRoles(value = {"admin"})
    public ModelAndView goodAdd(){
        ModelAndView modelAndView=new ModelAndView();
        List<Types> list = typesService.list();
        modelAndView.addObject("typeList",list);
        modelAndView.setViewName("/admin/good_add.jsp");
        return modelAndView;
    }

    /**
     * 跳转商品管理页面
     * */
    @GetMapping(value = "/goodList")
    @RequiresRoles(value = {"admin"})
    public ModelAndView goodList(@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 @RequestParam(value ="pageNo",defaultValue = "1")Integer pageNo,
                                 @RequestParam(value = "type",required = false,defaultValue = "0") Integer type){
        PageHelper.startPage(pageNo,pageSize);
        ModelAndView modelAndView=new ModelAndView();
        List<Goods> list = goodsService.selectGoodsAdmin(type);
        PageInfo<Goods> pageInfo=new PageInfo<>(list);
        int page = pageInfo.getPageNum();
        List<Integer>list1=new ArrayList<>();
        for (int i=page;i<page+5;i++){
            list1.add(i);
        }
        if (type==2){
            modelAndView.addObject("type",2);
        }
        modelAndView.addObject("pages",list1);
        modelAndView.addObject("goodList",pageInfo);
        modelAndView.setViewName("/admin/good_list.jsp");
        return modelAndView;
    }

    @GetMapping(value = "goodEdit")
    @RequiresRoles(value = {"admin"})
    public ModelAndView goodEdit(Integer id){
        ModelAndView modelAndView=new ModelAndView();
        List<Goods> list = goodsService.list(new QueryWrapper<Goods>().select("content","id", "cover", "name", "intro", "spec", "price", "stock").eq("id", id));
        List<Goods> goods = goodsService.selectGoodsType();
        modelAndView.addObject("typeList",goods);
        modelAndView.addObject("good",list.get(0));
        modelAndView.setViewName("/admin/good_edit.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/goodDelete")
    @RequiresRoles(value = {"admin"})
    public ModelAndView goodDelete(@RequestParam(value = "id") Integer id, @RequestParam(value = "type",required = false,defaultValue = "0") Integer type){
        goodsService.remove(new LambdaUpdateWrapper<Goods>().eq(Goods::getId,id));
        PageHelper.startPage(1,5);
        ModelAndView modelAndView=new ModelAndView();
        List<Goods> list = goodsService.selectGoodsAdmin(type);
        PageInfo<Goods> pageInfo=new PageInfo<>(list);
        int page = pageInfo.getPageNum();
        List<Integer>list1=new ArrayList<>();
        for (int i=page;i<page+5;i++){
            list1.add(i);
        }
        if (type==2){
            modelAndView.addObject("type",2);
        }
        modelAndView.addObject("pages",list1);
        modelAndView.addObject("goodList",pageInfo);
        modelAndView.setViewName("/admin/good_list.jsp");
        return modelAndView;
    }



}
