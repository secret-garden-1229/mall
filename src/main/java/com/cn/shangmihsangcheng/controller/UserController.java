package com.cn.shangmihsangcheng.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cn.shangmihsangcheng.domain.Users;
import com.cn.shangmihsangcheng.service.UsersService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UsersService usersService;

    @GetMapping(value = "/updateAddres")
    @RequiresRoles(value = {"user"})
    public boolean updateAddres(HttpSession session, @RequestParam(value = "username") String username, @RequestParam(value = "address") String address){
        Users user = (Users) session.getAttribute("user");
        Integer id = user.getId();
        UpdateWrapper<Users> updateWrapper=new UpdateWrapper();
        updateWrapper.lambda().set(Users::getName,username).set(Users::getAddress,address).eq(Users::getId,id);
        boolean update = usersService.update(updateWrapper);
        return update;
    }

    @PostMapping("/updatePassword")
    @RequiresRoles(value = {"Admin"})
    public String updatePassword(HttpSession session,@RequestParam(value = "oldPassword")String oldPassword,@RequestParam(value = "newPassword")String newPassword){
        Users user = (Users) session.getAttribute("user");
        String password = user.getPassword();
        Integer id=user.getId();
        if (oldPassword.equals(newPassword)){
            return "新密码和老密码不能一样！";
        }
        if (oldPassword.equals(password)){
            UpdateWrapper<Users> updateWrapper=new UpdateWrapper<>();
            updateWrapper.lambda().set(Users::getPassword,newPassword).eq(Users::getId,id);
            usersService.update(updateWrapper);
            session.removeAttribute("user");
            return "true";
        }
           return "请输入确认原密码正确！";
    }

    @GetMapping(value = "/userList")
    @RequiresRoles(value = {"admin"})
    public ModelAndView userList(){
        ModelAndView modelAndView=new ModelAndView();
        List<Users> list = usersService.list();
        modelAndView.addObject("userList",list);
        modelAndView.setViewName("/admin/user_list.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/user_reset")
    @RequiresRoles(value = {"admin"})
    public ModelAndView user_reset(Users users){
        boolean b = usersService.updateById(users);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/user_reset.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/userReset")
    @RequiresRoles(value = {"admin"})
    public ModelAndView userReset(Users users,HttpSession session){
        boolean b = usersService.updateById(users);
        session.removeAttribute("administratorUser");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/login.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/userEdit")
    @RequiresRoles(value = {"admin"})
    public ModelAndView userEdit(Integer id){
        Users one = usersService.getOne(new LambdaQueryWrapper<Users>().eq(Users::getId, id));
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("user",one);
        modelAndView.setViewName("/admin/user_edit.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/userUpdate")
    @RequiresRoles(value = {"admin"})
    public ModelAndView userUpdate(Users users){
        boolean b = usersService.saveOrUpdate(users);
        ModelAndView modelAndView=new ModelAndView();
        List<Users> list = usersService.list();
        modelAndView.addObject("userList",list);
        modelAndView.setViewName("/admin/user_list.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/userDelete")
    @RequiresRoles(value = {"admin"})
    public ModelAndView userDelete(Integer id){
        usersService.remove(new LambdaQueryWrapper<Users>().eq(Users::getId,id));
        ModelAndView modelAndView=new ModelAndView();
        List<Users> list = usersService.list();
        modelAndView.addObject("userList",list);
        modelAndView.setViewName("/admin/user_list.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/redirect/user_add")
    @RequiresRoles(value = {"admin"})
    public ModelAndView redirectUserAdd(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/user_add.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/userSave")
    @RequiresRoles(value = {"admin"})
    public ModelAndView userSave(Users users){
        usersService.saveOrUpdate(users);
        ModelAndView modelAndView=new ModelAndView();
        List<Users> list = usersService.list();
        modelAndView.addObject("userList",list);
        modelAndView.setViewName("/admin/user_list.jsp");
        return modelAndView;
    }



}
