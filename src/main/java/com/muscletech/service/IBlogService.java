package com.muscletech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muscletech.entity.Blog;

import java.util.List;

public interface IBlogService extends IService<Blog> {

    Blog updateBlog(Integer id, Blog blog);

    List<Blog> findSelf(Integer id);

    Blog get(Integer id);
}
