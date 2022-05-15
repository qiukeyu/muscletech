package com.muscletech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muscletech.controller.dto.UserDTO;
import com.muscletech.controller.dto.UserPasswordDTO;
import com.muscletech.entity.User;

public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    UserDTO loginByVerification(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    User get(Integer id);
}
