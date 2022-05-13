package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserPasswordDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;


    @PostMapping("/login/verification")
    @ApiOperation(value = "验证码登录(验证码不用给后端)，未注册的手机号自动注册登录", notes = "userDTO.userPhoneNumber")
    public Result loginByVerification(@RequestBody UserDTO userDTO) {
        String phoneNumber = userDTO.getUserPhoneNumber();
        if (StrUtil.isBlank(phoneNumber)) {
            return Result.error(Constants.LACK, "lack of ");
        }
        UserDTO dto = userService.loginByVerification(userDTO);
        return Result.success(dto);
    }


    @PostMapping("/login/password")
    @ApiOperation(value = "密码登录（前提是账号设置了密码）", notes = "userDTO.userPhoneNumber\nuserDTO.userPassword")
    public Result loginByPassword(@RequestBody UserDTO userDTO) {
        String phoneNumber = userDTO.getUserPhoneNumber();
        String password = userDTO.getUserPassword();
        if (StrUtil.isBlank(phoneNumber) || StrUtil.isBlank(password)) {
            return Result.error(Constants.LACK, "lack of phone number or password");
        }
        try {
            UserDTO dto = userService.login(userDTO);
            return Result.success(dto);
        } catch (ServiceException e) {
            return Result.error(e.getCode(), e.getMessage());
        }
    }


    @PostMapping("/password")
    @ApiOperation(value = "修改密码", notes = "userPasswordDTO.phoneNumber\nuserPasswordDTO.password\nuserPasswordDTO.newPassword")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
        String number = userPasswordDTO.getPhoneNumber();
        String password = userPasswordDTO.getPassword();
        String newPassword = userPasswordDTO.getNewPassword();
        if (StrUtil.isBlank(number) || StrUtil.isBlank(password) || StrUtil.isBlank(newPassword))
            return Result.error(Constants.LACK, "lack of phone number, password or new password");
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "注销用户", notes = "id")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        userService.remove(queryWrapper);
        return Result.success();
    }


    @GetMapping
    @ApiOperation(value = "提供给后台管理查看所有用户", notes = "")
    public Result findAll() {
        return Result.success(userService.list());
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "查看某用户", notes = "id")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.get(id));
    }

}
