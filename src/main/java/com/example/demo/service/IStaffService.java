package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.entity.Staff;

import java.util.List;

public interface IStaffService extends IService<Staff> {

    StaffDTO login(StaffDTO staffDTO);

    Staff add(StaffDTO staffDTO);

    List<Staff> findAll();

    Staff get(Integer id);

    Staff updateStaff(Integer id, StaffDTO staffDTO);

    Staff addManager(StaffDTO managerDTO);
}
