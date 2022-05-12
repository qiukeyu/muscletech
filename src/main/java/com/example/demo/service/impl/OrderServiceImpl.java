package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.entity.Venue;
import com.example.demo.entity.VenueOrder;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.VenueMapper;
import com.example.demo.service.IOrderService;
import com.example.demo.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, VenueOrder> implements IOrderService {

    @Resource
    OrderMapper orderMapper;

    @Resource
    VenueMapper venueMapper;

    @Override
    public List<VenueOrder> findAll(Integer id) {
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
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

    @Override
    public VenueOrder add(VenueOrder order) {
        QueryWrapper<Venue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", order.getVenueId());
        Venue venue;
        try {
            venue = venueMapper.selectOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        Integer venuePrice = venue.getPrice();
        Integer coachPrice = venue.getCoachPrice();
        if (order.getCoach() == 0) {
            order.setPrice(venuePrice);
        } else {
            order.setPrice(venuePrice + coachPrice);
        }
        save(order);
        return order;
    }

}
