package com.muscletech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muscletech.controller.dto.StaffDTO;
import com.muscletech.entity.Staff;

import java.util.List;

public interface IStaffService extends IService<Staff> {

    StaffDTO login(StaffDTO staffDTO);

    Staff add(StaffDTO staffDTO);

    List<Staff> findAll();

    Staff get(Integer id);

    Staff updateStaff(Integer id, StaffDTO staffDTO);

    Staff addManager(StaffDTO managerDTO);
}
