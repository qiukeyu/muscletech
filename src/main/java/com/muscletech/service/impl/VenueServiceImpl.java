package com.muscletech.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muscletech.common.Constants;
import com.muscletech.entity.Center;
import com.muscletech.entity.Venue;
import com.muscletech.exception.ServiceException;
import com.muscletech.mapper.CenterMapper;
import com.muscletech.mapper.VenueMapper;
import com.muscletech.service.IVenueService;
import com.muscletech.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class VenueServiceImpl extends ServiceImpl<VenueMapper, Venue> implements IVenueService {

    @Resource
    private VenueMapper venueMapper;

    @Resource
    private CenterMapper centerMapper;

    @Override
    public  Venue add(Venue venue) {
        Integer centerId = Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId();
        QueryWrapper<Center> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("center_id", centerId);
        Center center = centerMapper.selectOne(queryWrapper);
        venue.setCenterId(centerId);
        venue.setCenterName(center.getCenterName());
        save(venue);
        return venue;
    }


    @Override
    public Venue updateVenue(Integer id, Venue venue) {

        UpdateWrapper<Venue> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("venue_id", id);
        Venue one = getOne(updateWrapper);


        String name = venue.getVenueName();
        Integer capacity = venue.getCapacity();
        Integer price = venue.getPrice();
        String coach = venue.getCoach();
        Integer coachPrice = venue.getCoachPrice();

        if (!StrUtil.isBlank(name)) {
            one.setVenueName(name);
            one.setVenueType(venue.getVenueType());
        }
        if (capacity != null)
            one.setCapacity(capacity);
        if (price != null)
            one.setPrice(price);
        if (!StrUtil.isBlank(coach))
            one.setCoach(coach);
        if (coachPrice != null)
            one.setCoachPrice(coachPrice);
        if (venue.getOpen() != null)
            one.setOpen(venue.getOpen());

        venueMapper.update(one, updateWrapper);
        return one;
    }

    @Override
    public List<Venue> findAll() {
        QueryWrapper<Venue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("center_id", TokenUtils.getCurrentStaff().getCenterId());
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

    @Override
    public List<Venue> find(String venue, String  center) {
        QueryWrapper<Venue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_name", venue);
        queryWrapper.eq("center_name", center);
        queryWrapper.orderByDesc("venue_id");
        List<Venue> venueList = venueMapper.selectList(queryWrapper);
        return venueList;
    }
}
