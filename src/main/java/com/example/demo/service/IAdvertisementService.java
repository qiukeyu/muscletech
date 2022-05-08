package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Advertisement;

public interface IAdvertisementService extends IService<Advertisement> {

    Advertisement updateAdvertisement(Integer id, Advertisement advertisement);

    Advertisement get(Integer id);
}
