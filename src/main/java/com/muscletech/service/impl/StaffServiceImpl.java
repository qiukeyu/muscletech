package com.muscletech.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muscletech.common.Constants;
import com.muscletech.controller.dto.StaffDTO;
import com.muscletech.entity.Staff;
import com.muscletech.exception.ServiceException;
import com.muscletech.mapper.StaffMapper;
import com.muscletech.service.IStaffService;
import com.muscletech.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    @Resource
    private StaffMapper staffMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public StaffDTO login(StaffDTO staffDTO) {
        Staff one = getStaff(staffDTO);
        if (one == null) {
            throw new ServiceException(Constants.WRONG, "staff number wrong");
        } else if (passwordEncoder.matches(staffDTO.getStaffPassword(),one.getStaffPassword())){
            BeanUtil.copyProperties(one, staffDTO, true);
            String token = TokenUtils.genToken(one.getStaffId().toString(), one.getStaffPassword());
            staffDTO.setToken(token);
            return staffDTO;
        } else {
            throw new ServiceException(Constants.WRONG, "password wrong");
        }
    }

    @Override
    public Staff add(StaffDTO staffDTO) {
        Staff one;
        if (!checkStaffExist(staffDTO)) {
            one = new Staff();
            staffDTO.setRole("ROLE_STAFF");
            BeanUtil.copyProperties(staffDTO, one, true);
            one.setManagerId(TokenUtils.getCurrentStaff().getStaffId());
            one.setCenterId(TokenUtils.getCurrentStaff().getCenterId());
            one.setStaffPassword(passwordEncoder.encode(staffDTO.getStaffPassword()));
            save(one);
        } else {
            throw new ServiceException(Constants.WRONG, "staff number exist");
        }
        return one;
    }

    private Staff getStaff(StaffDTO staffDTO) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("staff_number", staffDTO.getStaffNumber());
        Staff one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        return one;
    }

    private Boolean checkStaffExist(StaffDTO staffDTO) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("staff_number", staffDTO.getStaffNumber());
        return getOne(queryWrapper) != null;
    }

    @Override
    public List<Staff> findAll() {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("manager_id", TokenUtils.getCurrentStaff().getStaffId());
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
        if (staffDTO.getStaffPassword() != null)
            one.setStaffPassword(passwordEncoder.encode(staffDTO.getStaffPassword()));
        if (staffDTO.getStaffNumber() != null)
            one.setStaffNumber(staffDTO.getStaffNumber());
        if (staffDTO.getStaffFirstName() != null)
            one.setStaffFirstName(staffDTO.getStaffFirstName());
        if (staffDTO.getStaffLastName() != null)
            one.setStaffLastName(staffDTO.getStaffLastName());
        staffMapper.update(one, updateWrapper);
        return one;
    }

    @Override
    public Staff addManager(StaffDTO managerDTO) {
        Staff one;
        if (!checkStaffExist(managerDTO)) {
            one = new Staff();
            managerDTO.setRole("ROLE_MANAGER");
            BeanUtil.copyProperties(managerDTO, one, true);
            one.setStaffPassword(passwordEncoder.encode(managerDTO.getStaffPassword()));
            save(one);
        } else {
            throw new ServiceException(Constants.WRONG, "staff number exist");
        }
        return one;
    }
}
