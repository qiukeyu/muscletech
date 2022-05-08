package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserPasswordDTO;
import com.example.demo.entity.User;

public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    UserDTO loginByVerification(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    User get(Integer id);
}
