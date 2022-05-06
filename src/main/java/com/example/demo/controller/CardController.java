package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Card;
import com.example.demo.service.ICardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/card")
public class CardController {

    @Resource
    private ICardService cardService;


    @PostMapping("/add")
    public Result add(@RequestBody Card card){

        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Card card){

        return Result.success();
    }

    @GetMapping()
    public Result findAll(){
        return Result.success(cardService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(cardService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(cardService.removeById(id));
    }

}
