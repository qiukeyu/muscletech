package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.StaffDTO;
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
        String number = staffDTO.getNumber();
        String password = staffDTO.getPassword();
        if (StrUtil.isBlank(number) || StrUtil.isBlank(password)) {
            return Result.error(Constants.LACK);
        }
        StaffDTO dto = staffService.login(staffDTO);
        return Result.success(dto);
    }

    @PostMapping("/add")
    public Result add(@RequestBody StaffDTO staffDTO) {
        String number = staffDTO.getNumber();
        String password = staffDTO.getPassword();
        String firstName = staffDTO.getFirstName();
        String lastName = staffDTO.getLastName();
        if (StrUtil.isBlank(number) || StrUtil.isBlank(password) || StrUtil.isBlank(firstName) || StrUtil.isBlank(lastName)) {
            return Result.error(Constants.LACK, "lack of information");
        }
        return Result.success(staffService.add(staffDTO));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(staffService.removeById(id));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(staffService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(staffService.getById(id));
    }

}