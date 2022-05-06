package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.entity.Staff;

public interface IStaffService extends IService<Staff> {

    StaffDTO login(StaffDTO staffDTO);

    Staff add(StaffDTO userDTO);
}
