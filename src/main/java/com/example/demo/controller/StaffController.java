package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.entity.Staff;
import com.example.demo.service.IStaffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Resource
    private IStaffService staffService;

    @PostMapping("/login")
    public Result login(@RequestBody StaffDTO staffDTO) {
        String number = staffDTO.getStaffNumber();
        String password = staffDTO.getStaffPassword();
        if (StrUtil.isBlank(number) || StrUtil.isBlank(password)) {
            return Result.error(Constants.LACK);
        }
        StaffDTO dto = staffService.login(staffDTO);
        return Result.success(dto);
    }

    @PostMapping()
    public Result add(@RequestBody StaffDTO staffDTO) {
        String number = staffDTO.getStaffNumber();
        String password = staffDTO.getStaffPassword();
        String firstName = staffDTO.getStaffFirstName();
        String lastName = staffDTO.getStaffLastName();
        if (StrUtil.isBlank(number) || StrUtil.isBlank(password) || StrUtil.isBlank(firstName) || StrUtil.isBlank(lastName)) {
            return Result.error(Constants.LACK, "lack of information");
        }
        return Result.success(staffService.add(staffDTO));
    }

    @PostMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody StaffDTO staffDTO) {
        return Result.success(staffService.updateStaff(id, staffDTO));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("staff_id", id);
        staffService.remove(queryWrapper);
        return Result.success();
    }

    @GetMapping
    public Result findAll(@RequestBody StaffDTO managerDTO) {
        return Result.success(staffService.findAll(managerDTO));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(staffService.get(id));
    }

}