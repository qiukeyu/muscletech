package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.mapper.BlogMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IBlogService;
import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
}
