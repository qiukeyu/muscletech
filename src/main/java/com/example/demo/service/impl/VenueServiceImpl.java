package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Staff;
import com.example.demo.entity.Venue;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.VenueMapper;
import com.example.demo.service.IVenueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VenueServiceImpl extends ServiceImpl<VenueMapper, Venue> implements IVenueService {

    @Resource
    VenueMapper venueMapper;

    @Override
    public Venue updateVenue(Integer id, Venue venue) {
        UpdateWrapper<Venue> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("venue_id", id);
        Venue one = getOne(updateWrapper);
        BeanUtil.copyProperties(venue, one, true);
        venueMapper.update(one, updateWrapper);
        return one;
    }

    @Override
    public List<Venue> findAll(StaffDTO staffDTO) {
        QueryWrapper<Venue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("center_id", staffDTO.getCenterId());
        queryWrapper.orderByDesc("venue_id");
        List<Venue> venueList = venueMapper.selectList(queryWrapper);
        return venueList;
    }

    @Override
    public Venue get(Integer id) {
        QueryWrapper<Venue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        Venue one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        return one;
    }
}
