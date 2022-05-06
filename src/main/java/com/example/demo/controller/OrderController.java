package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.VenueOrder;
import com.example.demo.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;


    @PostMapping("/add")
    public Result add(@RequestBody VenueOrder order){

        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody VenueOrder order){

        return Result.success();
    }

    @GetMapping()
    public Result findAll(){
        return Result.success(orderService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(orderService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(orderService.removeById(id));
    }
}
