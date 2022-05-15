package com.muscletech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muscletech.entity.Venue;

import java.util.List;

public interface IVenueService extends IService<Venue> {

    Venue updateVenue(Integer id, Venue venue);

    Venue add(Venue venue);

    List<Venue> findAll();

    Venue get(Integer id);

    List<Venue> find(String venue, String  center);
}
