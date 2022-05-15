package com.muscletech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muscletech.controller.dto.UserPasswordDTO;
import com.muscletech.entity.User;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {

    @Update("update user set user_password = #{newPassword} where user_phone_number = #{phoneNumber}")
    int setPassword(UserPasswordDTO userPasswordDTO);
}
