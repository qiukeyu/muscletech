package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.CenterVenue;
import com.example.demo.mapper.CenterVenueMapper;
import com.example.demo.service.IVenueService;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl extends ServiceImpl<CenterVenueMapper, CenterVenue> implements IVenueService {
}
