package com.muscletech.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muscletech.common.Constants;
import com.muscletech.controller.dto.UserDTO;
import com.muscletech.controller.dto.UserPasswordDTO;
import com.muscletech.entity.User;
import com.muscletech.exception.ServiceException;
import com.muscletech.mapper.UserMapper;
import com.muscletech.service.IUserService;
import com.muscletech.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO loginByVerification(UserDTO userDTO) {
        User one = getUser(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
        } else {
            one = new User();
            userDTO.setRole("ROLE_USER");
            userDTO.setUserName("user_" + System.currentTimeMillis());
            BeanUtil.copyProperties(userDTO, one, true);
            save(one);
            one = getUser(userDTO);
        }
        String token = TokenUtils.genToken(one.getUserId().toString(), "verification");
        userDTO.setToken(token);
        return userDTO;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUser(userDTO);
        if (one == null) {
            throw new ServiceException(Constants.LACK, "phone number wrong");
        } else if (one.getUserPassword() == null) {
            throw new ServiceException(Constants.WRONG, "No password set");
        } else {
            if (passwordEncoder.matches(userDTO.getUserPassword(),one.getUserPassword())) {
//            if (userDTO.getUserPassword().equals(one.getUserPassword())) {
                BeanUtil.copyProperties(one, userDTO, true);
                String token = TokenUtils.genToken(one.getUserId().toString(), one.getUserPassword());
                userDTO.setToken(token);
                return userDTO;
            } else {
                throw new ServiceException(Constants.LACK, "password wrong");
            }
        }
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        //User one = getUser(userPasswordDTO);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", Objects.requireNonNull(TokenUtils.getCurrentUser()).getUserId());
        User one = getOne(updateWrapper);
        one.setUserPassword(passwordEncoder.encode(userPasswordDTO.getNewPassword()));
        userMapper.update(one, updateWrapper);
//        if (userPasswordDTO.getPassword() != null) {
//            if (passwordEncoder.matches(userPasswordDTO.getPassword(),one.getUserPassword()))
//                userMapper.setPassword(userPasswordDTO);
//            else
//                throw new ServiceException(Constants.WRONG, "password wrong");
//        } else {
//            userMapper.setPassword(userPasswordDTO);
//        }
    }

    private User getUser(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone_number", userDTO.getUserPhoneNumber());
        User one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "System error");
        }
        return one;
    }

//    private User getUser(UserPasswordDTO userPasswordDTO) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_phone_number", userPasswordDTO.getPhoneNumber());
//        User one;
//        try {
//            one = getOne(queryWrapper);
//        } catch (Exception e) {
//            throw new ServiceException(Constants.ERROR, "System error");
//        }
//        return one;
//    }

    @Override
    public User get(Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        User one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        return one;
    }
}
