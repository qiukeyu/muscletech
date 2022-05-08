package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Blog;
import com.example.demo.service.IBlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private IBlogService blogService;


    @PostMapping("/add")
    public Result add(@RequestBody Blog blog) {

        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Blog blog) {

        return Result.success();
    }

    @GetMapping()
    public Result findAll() {
        return Result.success(blogService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(blogService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(blogService.removeById(id));
    }
}
