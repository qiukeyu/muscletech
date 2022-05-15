package com.muscletech.controller;

import com.muscletech.common.Result;
import com.muscletech.entity.VenueOrder;
import com.muscletech.service.IOrderService;
import com.muscletech.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @PostMapping()
    @ApiOperation(value = "增加订单", notes = "venueId\norderDate\norderTime(第几场)")
    public Result add(@RequestBody VenueOrder order) {
        order.setCoach(0);
        order.setUserId(Objects.requireNonNull(TokenUtils.getCurrentUser()).getUserId());
        return Result.success(orderService.add(order));
    }


    @GetMapping()
    @ApiOperation(value = "当前用户的所有订单", notes = "")
    public Result findAll() {
        return Result.success(orderService.findAll(Objects.requireNonNull(TokenUtils.getCurrentUser()).getUserId()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "某次订单", notes = "")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(orderService.get(id));
    }

}
