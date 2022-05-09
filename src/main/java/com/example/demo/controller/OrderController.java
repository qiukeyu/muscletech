package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.Venue;
import com.example.demo.entity.VenueOrder;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.IOrderService;
import com.example.demo.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    // 增加订单
    // 需要venueId,参数为venueId
    @PostMapping("/{id}")
    public Result add(@PathVariable Integer id, @RequestBody VenueOrder order) {
        order.setUserId(TokenUtils.getCurrentUser().getUserId());
        order.setVenueId(id);
        return Result.success(orderService.add(order));
    }

    // 当前用户的所有订单
    @GetMapping()
    public Result findAll() {
        return Result.success(orderService.findAll(TokenUtils.getCurrentUser().getUserId()));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(orderService.get(id));
    }

}
