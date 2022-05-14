package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.DataDTO;
import com.example.demo.entity.Venue;
import com.example.demo.entity.VenueOrder;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.VenueMapper;
import com.example.demo.service.IOrderService;
import com.example.demo.service.IStaffService;
import com.example.demo.service.IUserService;
import com.example.demo.utils.TokenUtils;
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
        dataDTO.setBadminton(getBadminton());
        dataDTO.setGym(getGym());
        dataDTO.setBasketball(getBasketball());
        dataDTO.setSwimming(getSwimming());
        dataDTO.setTennis(getTennis());
        dataDTO.setTableTennis(getTableTennis());
        return Result.success(dataDTO);
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

    private Integer getBasketball() {
        QueryWrapper<Venue> venueQueryWrapper = new QueryWrapper<>();
        venueQueryWrapper.eq("center_id", Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId());
        venueQueryWrapper.eq("venue_name", "Basketball");
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
        venueQueryWrapper.eq("venue_name", "TableTennis");
        Integer id = venueMapper.selectOne(venueQueryWrapper).getVenueId();
        QueryWrapper<VenueOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        return orderMapper.selectList(queryWrapper).size();
    }
}
