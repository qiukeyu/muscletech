package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.CenterVenue;
import com.example.demo.service.ICenterVenueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/venue")
public class CenterVenueController {

    @Resource
    private ICenterVenueService centervenueService;


    @PostMapping("/add")
    public Result add(@RequestBody CenterVenue centervenue){

        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody CenterVenue centervenue){

        return Result.success();
    }

    @GetMapping()
    public Result findAll(){
        return Result.success(centervenueService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(centervenueService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(centervenueService.removeById(id));
    }

}
