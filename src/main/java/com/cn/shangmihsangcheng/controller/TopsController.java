package com.cn.shangmihsangcheng.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cn.shangmihsangcheng.domain.Goods;
import com.cn.shangmihsangcheng.domain.Tops;
import com.cn.shangmihsangcheng.service.GoodsService;
import com.cn.shangmihsangcheng.service.TopsService;
import com.github.pagehelper.PageInfo;
;
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
public class TopsController {
    @Resource
    private TopsService topsService;

    @Resource
    private GoodsService goodsService;
    
    @PostMapping(value = "/topDelete")
    @RequiresRoles(value = {"admin"})
    public String topDelete(@RequestParam(value = "goodId") Integer goodId, @RequestParam(value = "type",required =false)Integer type){
        boolean remove = topsService.remove(new LambdaUpdateWrapper<Tops>().eq(Tops::getGoodId, goodId));
        String star=remove==true?"ok":"false";
        return star;
    }

    @PostMapping(value = "/topSave")
    @RequiresRoles(value = {"admin"})
    public String topSave(Tops tops){
        boolean b = topsService.saveOrUpdate(tops);
        String star=b==true?"ok":"false";
        return star;
    }

    
}
