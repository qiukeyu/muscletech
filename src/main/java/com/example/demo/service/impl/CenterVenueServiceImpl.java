package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.CenterVenue;
import com.example.demo.mapper.CenterVenueMapper;
import com.example.demo.service.ICenterVenueService;
import org.springframework.stereotype.Service;

@Service
public class CenterVenueServiceImpl extends ServiceImpl<CenterVenueMapper, CenterVenue> implements ICenterVenueService {
}
