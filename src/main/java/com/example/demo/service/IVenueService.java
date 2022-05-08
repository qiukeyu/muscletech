package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.entity.Staff;
import com.example.demo.entity.Venue;

import java.util.List;

public interface IVenueService extends IService<Venue> {

    Venue updateVenue(Integer id, Venue venue);

    List<Venue> findAll(StaffDTO staffDTO);

    Venue get(Integer id);
}
