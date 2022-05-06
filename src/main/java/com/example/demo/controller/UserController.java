package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserPasswordDTO;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;


    @PostMapping("/login/verification")
    public Result loginByVerification(@RequestBody UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        if (StrUtil.isBlank(phoneNumber)) {
            return Result.error(Constants.LACK);
        }
        UserDTO dto = userService.loginByVerification(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/login/password")
    public Result loginByPassword(@RequestBody UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(phoneNumber) || StrUtil.isBlank(password)) {
            return Result.error(Constants.LACK);
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/username/{username}")
    public Result findByUsername(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Result.success(userService.getOne(queryWrapper));
    }

}
