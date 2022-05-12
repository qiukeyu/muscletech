package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.entity.Blog;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.BlogMapper;
import com.example.demo.service.IBlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public Blog updateBlog(Integer id, Blog blog) {
        UpdateWrapper<Blog> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("blog_id", id);
        Blog one = getOne(updateWrapper);
        if (blog.getBlogTitle() != null)
            one.setBlogTitle(blog.getBlogTitle());
        if (blog.getBlogContent() != null)
            one.setBlogContent(blog.getBlogContent());
        blogMapper.update(one, updateWrapper);
        return one;
    }

    @Override
    public List<Blog> findSelf(Integer id) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        queryWrapper.orderByDesc("blog_id");
        List<Blog> blogList = blogMapper.selectList(queryWrapper);
        return blogList;
    }

    @Override
    public Blog get(Integer id) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id", id);
        Blog one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        return one;
    }

}
