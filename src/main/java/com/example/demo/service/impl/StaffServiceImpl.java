package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.entity.Staff;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.service.IStaffService;
import com.example.demo.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    @Resource
    private StaffMapper staffMapper;

    @Override
    public StaffDTO login(StaffDTO staffDTO) {
        Staff one = getStaffInfo(staffDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, staffDTO, true);
            String token = TokenUtils.genToken(one.getStaffNumber(), one.getStaffPassword());
            staffDTO.setToken(token);
            return staffDTO;
        } else {
            throw new ServiceException(Constants.WRONG, "staff number or password error");
        }
    }

    @Override
    public Staff add(StaffDTO staffDTO){
        Staff one = getStaffInfo(staffDTO);
        if (one == null) {
            one = new Staff();
            staffDTO.setRole("ROLE_STAFF");
            BeanUtil.copyProperties(staffDTO, one, true);
            save(one);
        } else {
            throw new ServiceException(Constants.WRONG, "staff number exist");
        }
        return one;
    }

    private Staff getStaffInfo(StaffDTO staffDTO) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("staff_number", staffDTO.getNumber());
        queryWrapper.eq("staff_password", staffDTO.getPassword());
        Staff one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        return one;
    }

    @Override
    public List<Staff> findAll(StaffDTO managerDTO) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("manager_id", managerDTO.getId());
        queryWrapper.orderByDesc("staff_id");
        List<Staff> staffList = staffMapper.selectList(queryWrapper);
        return staffList;
    }

    @Override
    public Staff get(Integer id) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("staff_id", id);
        Staff one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        return one;
    }

    @Override
    public Staff updateStaff(Integer id, StaffDTO staffDTO) {
        UpdateWrapper<Staff> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("staff_id", id);
        Staff one = getOne(updateWrapper);
        BeanUtil.copyProperties(staffDTO, one, true);
        staffMapper.update(one, updateWrapper);
        return one;
    }
}
