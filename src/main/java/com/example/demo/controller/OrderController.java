package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.VenueOrder;
import com.example.demo.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    // 增加订单
    // 需要userID和venueId,参数为venueId,以及已登录用户userDTO
    @PostMapping("/{id}")
    public Result add(@PathVariable Integer id, @RequestBody VenueOrder order, @RequestBody UserDTO userDTO) {
        order.setUserId(userDTO.getUserId());
        order.setVenueId(id);
        return Result.success(orderService.save(order));
    }

    // 当前用户的所有订单
    @GetMapping()
    public Result findAll(@RequestBody UserDTO userDTO) {
        return Result.success(orderService.findAll(userDTO));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(orderService.get(id));
    }

}
