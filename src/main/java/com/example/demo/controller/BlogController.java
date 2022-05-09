package com.example.demo.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.Blog;
import com.example.demo.entity.Venue;
import com.example.demo.service.IBlogService;
import com.example.demo.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private IBlogService blogService;

    // 新建blog
    @PostMapping()
    public Result add(@RequestBody Blog blog) {
        blog.setUserId(TokenUtils.getCurrentUser().getUserId());
        DateTime dateTime = new DateTime();
        blog.setBlogDateTime(dateTime.toString("yyyy-MM-dd hh:mm:ss"));
        return Result.success(blogService.save(blog));
    }

    // 修改blog,需要blogId
    @PostMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Blog blog) {
        return Result.success(blogService.updateBlog(id, blog));
    }

    // 所有blog包括用户自己的
    @GetMapping()
    public Result findAll() {
        return Result.success(blogService.list());
    }

    // 当前用户的blog
    @GetMapping("/self")
    public Result findSelf(@RequestBody UserDTO userDTO) {
        return Result.success(blogService.findSelf(TokenUtils.getCurrentUser().getUserId()));
    }

    // 某个blog,根据blogId
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(blogService.get(id));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id", id);
        blogService.remove(queryWrapper);
        return Result.success();
    }
}
