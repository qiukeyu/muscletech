package com.muscletech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muscletech.common.Constants;
import com.muscletech.entity.Advertisement;
import com.muscletech.exception.ServiceException;
import com.muscletech.mapper.AdvertisementMapper;
import com.muscletech.service.IAdvertisementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, Advertisement> implements IAdvertisementService {

    @Resource
    private AdvertisementMapper advertisementMapper;

    public Advertisement updateAdvertisement(Integer id, Advertisement advertisement) {
        UpdateWrapper<Advertisement> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("advertisement_id", id);
        Advertisement one = getOne(updateWrapper);

        if (advertisement.getAdvertisementName() != null)
            one.setAdvertisementName(advertisement.getAdvertisementName());
        if (advertisement.getAdvertisementPicture() != null)
            one.setAdvertisementPicture(advertisement.getAdvertisementPicture());

        advertisementMapper.update(one, updateWrapper);
        return one;
    }

    @Override
    public Advertisement get(Integer id) {
        QueryWrapper<Advertisement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("advertisement_id", id);
        Advertisement one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        return one;
    }

}
