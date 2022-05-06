package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Advertisement;
import com.example.demo.service.IAdvertisementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Resource
    private IAdvertisementService advertisementService;


    @PostMapping("/add")
    public Result add(@RequestBody Advertisement advertisement){

        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Advertisement advertisement){

        return Result.success();
    }

    @GetMapping()
    public Result findAll(){
        return Result.success(advertisementService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(advertisementService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(advertisementService.removeById(id));
    }
}
