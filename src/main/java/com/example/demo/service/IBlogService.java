package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Blog;

import java.util.List;

public interface IBlogService extends IService<Blog> {

    Blog updateBlog(Integer id, Blog blog);

    List<Blog> findSelf(Integer id);

    Blog get(Integer id);
}
