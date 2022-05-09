package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserPasswordDTO;
import com.example.demo.entity.Staff;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    // 验证码登录，未注册的手机号自动注册登录
    @PostMapping("/login/verification")
    public Result loginByVerification(@RequestBody UserDTO userDTO) {
        String phoneNumber = userDTO.getUserPhoneNumber();
        if (StrUtil.isBlank(phoneNumber)) {
            return Result.error(Constants.LACK);
        }
        UserDTO dto = userService.loginByVerification(userDTO);
        return Result.success(dto);
    }

    // 密码登录（前提是账号设置了密码）
    @PostMapping("/login/password")
    public Result loginByPassword(@RequestBody UserDTO userDTO) {
        String phoneNumber = userDTO.getUserPhoneNumber();
        String password = userDTO.getUserPassword();
        if (StrUtil.isBlank(phoneNumber) || StrUtil.isBlank(password)) {
            return Result.error(Constants.LACK);
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    // 修改密码
    @PostMapping("/password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }

    // 注销用户
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        userService.remove(queryWrapper);
        return Result.success();
    }

    // 后台
    // 查看用户
    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }


    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.get(id));
    }

}
