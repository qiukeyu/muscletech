package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserPasswordDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO loginByVerification(UserDTO userDTO) {
        User one = getUser(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
        } else {
            one = new User();
            userDTO.setRole("ROLE_USER");
            userDTO.setUsername("user");
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
            throw new ServiceException(Constants.WRONG, "phone number wrong");
        } else if (one.getUserPassword() == null) {
            throw new ServiceException(Constants.WRONG, "No password set");
        } else {
//            if (passwordEncoder.matches(userDTO.getPassword(),one.getUserPassword())) {
            if (userDTO.getUserPassword().equals(one.getUserPassword())) {
                BeanUtil.copyProperties(one, userDTO, true);
                String token = TokenUtils.genToken(one.getUserId().toString(), one.getUserPassword());
                userDTO.setToken(token);
                return userDTO;
            } else {
                throw new ServiceException(Constants.WRONG, "password wrong");
            }
        }
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        User one = getUser(userPasswordDTO);
//        userPasswordDTO.setNewPassword(passwordEncoder.encode(userPasswordDTO.getNewPassword()));
        if (userPasswordDTO.getPassword() != null) {
//            if (passwordEncoder.matches(userPasswordDTO.getPassword(),one.getUserPassword()))
            if (true)
                userMapper.setPassword(userPasswordDTO);
            else
                throw new ServiceException(Constants.WRONG, "password wrong");
        } else {
            userMapper.setPassword(userPasswordDTO);
        }
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

    private User getUser(UserPasswordDTO userPasswordDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone_number", userPasswordDTO.getPhoneNumber());
        User one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "System error");
        }
        return one;
    }

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
