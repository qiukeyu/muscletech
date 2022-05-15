package com.muscletech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muscletech.entity.Advertisement;

public interface IAdvertisementService extends IService<Advertisement> {

    Advertisement updateAdvertisement(Integer id, Advertisement advertisement);

    Advertisement get(Integer id);
}
