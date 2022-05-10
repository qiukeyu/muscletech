package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.entity.Center;
import com.example.demo.entity.Staff;
import com.example.demo.service.IStaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Resource
    private IStaffService staffService;

    @PostMapping("/login")
    @ApiOperation(value = "员工登录", notes = "staffDTO.staffNumber\nstaffDTO.staffPassword")
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
    @ApiOperation(value = "经理添加员工", notes = "员工默认添加到当前登录经理及其运动中心下\nstaffDTO.staffNumber\nstaffDTO.staffPassword\nstaffDTO.staffFirstName\nstaffDTO.staffLastName")
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

    @ApiOperation(value = "更新员工信息，改头像的，暂时忽略")
    @PostMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody StaffDTO staffDTO) {
        return Result.success(staffService.updateStaff(id, staffDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除员工", notes = "id")
    public Result delete(@PathVariable Integer id) {
        if (id == null)
            return Result.error(Constants.LACK, "No id");
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("staff_id", id);
        staffService.remove(queryWrapper);
        return Result.success();
    }

    @GetMapping
    @ApiOperation(value = "当前登录经理下的所有员工")
    public Result findAll() {
        return Result.success(staffService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "某个员工", notes = "id")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(staffService.get(id));
    }

    @ApiOperation(value = "经理注册(需要关联到运动中心,即需要centerId，但是忽略，直接到数据库进行操作)", notes = "staffDTO.staffNumber\nstaffDTO.staffPassword\nstaffDTO.staffFirstName\nstaffDTO.staffLastName")
    @PostMapping("/manager")
    public Result managerRegister(@RequestBody StaffDTO managerDTO) {
        String number = managerDTO.getStaffNumber();
        String password = managerDTO.getStaffPassword();
        String firstName = managerDTO.getStaffFirstName();
        String lastName = managerDTO.getStaffLastName();
        if (StrUtil.isBlank(number) || StrUtil.isBlank(password) || StrUtil.isBlank(firstName) || StrUtil.isBlank(lastName)) {
            return Result.error(Constants.LACK, "lack of information");
        }
        return Result.success(staffService.addManager(managerDTO));
    }
}