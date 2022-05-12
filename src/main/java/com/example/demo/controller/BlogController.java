package com.example.demo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.Blog;
import com.example.demo.service.IBlogService;
import com.example.demo.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private IBlogService blogService;


    @PostMapping()
    @ApiOperation(value = "添加blog", notes = "blog.blogTitle\nblog.blogContent")
    public Result add(@RequestBody Blog blog) {
        if (StrUtil.isBlank(blog.getBlogTitle()) || StrUtil.isBlank(blog.getBlogContent()))
            return Result.error(Constants.LACK, "lack of title or content");
        blog.setUserId(Objects.requireNonNull(TokenUtils.getCurrentUser()).getUserId());
        DateTime dateTime = new DateTime();
        blog.setBlogDateTime(dateTime.toString("yyyy-MM-dd hh:mm:ss"));
        return Result.success(blogService.save(blog));
    }


    @PostMapping("/{id}")
    @ApiOperation(value = "修改blog", notes = "id\nblog.blogTitle\nblog.blogContent")
    public Result update(@PathVariable Integer id, @RequestBody Blog blog) {
        return Result.success(blogService.updateBlog(id, blog));
    }


    @GetMapping()
    @ApiOperation(value = "所有blog", notes = "")
    public Result findAll() {
        return Result.success(blogService.list());
    }


    @GetMapping("/self")
    @ApiOperation(value = "用户自己的blog", notes = "")
    public Result findSelf() {
        return Result.success(blogService.findSelf(Objects.requireNonNull(TokenUtils.getCurrentUser()).getUserId()));
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "某个blog", notes = "id")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(blogService.get(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除blog", notes = "id")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id", id);
        blogService.remove(queryWrapper);
        return Result.success();
    }
}
