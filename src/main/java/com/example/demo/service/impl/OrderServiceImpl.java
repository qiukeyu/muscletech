package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.Staff;
import com.example.demo.entity.Venue;
import com.example.demo.entity.VenueOrder;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, VenueOrder> implements IOrderService {

    @Resource
    OrderMapper orderMapper;

    @Override
    public List<VenueOrder> findAll(UserDTO userDTO) {
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userDTO.getUserId());
        queryWrapper.orderByDesc("order_id");
        List<VenueOrder> orderList = orderMapper.selectList(queryWrapper);
        return orderList;
    }

    @Override
    public VenueOrder get(Integer id) {
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", id);
        VenueOrder one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        return one;
    }

}
