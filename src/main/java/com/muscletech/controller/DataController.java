package com.muscletech.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muscletech.common.Result;
import com.muscletech.controller.dto.DataDTO;
import com.muscletech.entity.Venue;
import com.muscletech.entity.VenueOrder;
import com.muscletech.mapper.OrderMapper;
import com.muscletech.mapper.VenueMapper;
import com.muscletech.service.IOrderService;
import com.muscletech.service.IStaffService;
import com.muscletech.service.IUserService;
import com.muscletech.utils.TokenUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {

    @Resource
    private IUserService userService;

    @Resource
    private IStaffService staffService;

    @Resource
    private IOrderService orderService;

    @Resource
    private VenueMapper venueMapper;

    @Resource
    private OrderMapper orderMapper;

    @GetMapping()
    public Result getData() {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setUser(userService.list().size());
        dataDTO.setStaff(staffService.list().size());
        List<VenueOrder> list = orderService.list();
        dataDTO.setOrder(list.size());
        int income = 0;
        for (VenueOrder order: list) {
            income = income + order.getPrice();
        }
        dataDTO.setIncome(income);
        if (hasVenue("Badminton"))
            dataDTO.setBadminton(getBadminton());
        if (hasVenue("Gym"))
            dataDTO.setGym(getGym());
        if (hasVenue("Tennis"))
            dataDTO.setTennis(getTennis());
        if (hasVenue("Ping Pong"))
            dataDTO.setTableTennis(getTableTennis());
        if (hasVenue("Glof"))
            dataDTO.setBasketball(getGlof());
        if (hasVenue("Swimming"))
            dataDTO.setSwimming(getSwimming());
        return Result.success(dataDTO);
    }

    private boolean hasVenue(String venueName) {
        QueryWrapper<Venue> venueQueryWrapper = new QueryWrapper<>();
        venueQueryWrapper.eq("center_id", Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId());
        venueQueryWrapper.eq("venue_name", venueName);
        Venue venue = venueMapper.selectOne(venueQueryWrapper);
        if (venue == null)
            return false;
        return true;
    }

    private Integer getBadminton() {
        QueryWrapper<Venue> venueQueryWrapper = new QueryWrapper<>();
        venueQueryWrapper.eq("center_id", Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId());
        venueQueryWrapper.eq("venue_name", "Badminton");
        Integer id = venueMapper.selectOne(venueQueryWrapper).getVenueId();
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        return orderMapper.selectList(queryWrapper).size();
    }

    private Integer getGym() {
        QueryWrapper<Venue> venueQueryWrapper = new QueryWrapper<>();
        venueQueryWrapper.eq("center_id", Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId());
        venueQueryWrapper.eq("venue_name", "Gym");
        Integer id = venueMapper.selectOne(venueQueryWrapper).getVenueId();
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        return orderMapper.selectList(queryWrapper).size();
    }

    private Integer getGlof() {
        QueryWrapper<Venue> venueQueryWrapper = new QueryWrapper<>();
        venueQueryWrapper.eq("center_id", Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId());
        venueQueryWrapper.eq("venue_name", "Glof");
        Integer id = venueMapper.selectOne(venueQueryWrapper).getVenueId();
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        return orderMapper.selectList(queryWrapper).size();
    }

    private Integer getTennis() {
        QueryWrapper<Venue> venueQueryWrapper = new QueryWrapper<>();
        venueQueryWrapper.eq("center_id", Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId());
        venueQueryWrapper.eq("venue_name", "Tennis");
        Integer id = venueMapper.selectOne(venueQueryWrapper).getVenueId();
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        return orderMapper.selectList(queryWrapper).size();
    }

    private Integer getSwimming() {
        QueryWrapper<Venue> venueQueryWrapper = new QueryWrapper<>();
        venueQueryWrapper.eq("center_id", Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId());
        venueQueryWrapper.eq("venue_name", "Swimming");
        Integer id = venueMapper.selectOne(venueQueryWrapper).getVenueId();
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        return orderMapper.selectList(queryWrapper).size();
    }

    private Integer getTableTennis() {
        QueryWrapper<Venue> venueQueryWrapper = new QueryWrapper<>();
        venueQueryWrapper.eq("center_id", Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId());
        venueQueryWrapper.eq("venue_name", "Ping Pong");
        Integer id = venueMapper.selectOne(venueQueryWrapper).getVenueId();
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        return orderMapper.selectList(queryWrapper).size();
    }

}
