package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.StaffDTO;
import com.example.demo.entity.Venue;
import com.example.demo.service.IVenueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/venue")
public class VenueController {

    @Resource
    private IVenueService venueService;

    // 场馆名映射到场馆预约类型
    private final Map<String, Integer> venueType = Map.of("Badminton", 1,
            "Tennis", 1,
            "TableTennis", 1,
            "Gym", 0,
            "Swimming", 0,
            "Basketball", 1);

    // 增加venue, centerId是必需的
    @PostMapping()
    public Result add(@RequestBody Venue venue) {
        String name = venue.getVenueName();
        venue.setVenueType(venueType.get(name));
        if (StrUtil.isBlank(name)) {
            return Result.error(Constants.LACK, "lack of information");
        }
        return Result.success(venueService.save(venue));
    }

    // 修改更新
    @PostMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Venue venue) {
        return Result.success(venueService.updateVenue(id, venue));
    }

    // 已登录员工所属运动中心的所有venue
    @GetMapping()
    public Result findAll(@RequestBody StaffDTO staffDTO) {
        return Result.success(venueService.findAll(staffDTO));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        QueryWrapper<Venue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        venueService.remove(queryWrapper);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(venueService.removeById(id));
    }

}
