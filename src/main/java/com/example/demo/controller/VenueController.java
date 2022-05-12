package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.Venue;
import com.example.demo.service.IVenueService;
import com.example.demo.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

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

    @ApiOperation(value = "增加场馆", notes = "venue.venueName(允许添加的场馆名在代码里，如下：\"Badminton\"\"Tennis\"\"TableTennis\"\"Gym\"\"Swimming\"\"Basketball\")\nvenue.capacity\nvenue.price()\nvenue.coach()\nvenue.coachPrice")
    @PostMapping()
    public Result add(@RequestBody Venue venue) {
        String name = venue.getVenueName();
        Integer capacity = venue.getCapacity();
        Integer price = venue.getPrice();
        String coach = venue.getCoach();
        Integer coachPrice = venue.getCoachPrice();
        venue.setVenueType(venueType.get(name));
        if (StrUtil.isBlank(name) || capacity == 0 || price == 0 || StrUtil.isBlank(coach) || coachPrice == 0) {
            return Result.error(Constants.LACK, "lack of information");
        }
        venue.setOpen(0);
        venue.setCenterId(Objects.requireNonNull(TokenUtils.getCurrentStaff()).getCenterId());
        return Result.success(venueService.save(venue));
    }

    @ApiOperation(value = "修改场馆信息", notes = "id\nvenue.venueName(允许添加的场馆名在代码里，如下：\"Badminton\"\"Tennis\"\"TableTennis\"\"Gym\"\"Swimming\"\"Basketball\")\nvenue.capacity\nvenue.price()\nvenue.coach()\nvenue.coachPrice")
    @PostMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Venue venue) {
        String name = venue.getVenueName();
        if (!StrUtil.isBlank(name))
            venue.setVenueType(venueType.get(name));
        return Result.success(venueService.updateVenue(id, venue));
    }

    @ApiOperation(value = "当前登录员工所属运动中心的所有场馆")
    @GetMapping()
    public Result findAll() {
        return Result.success(venueService.findAll());
    }

    @ApiOperation(value = "某一场馆")
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(venueService.get(id));
    }

    @ApiOperation(value = "删除场馆")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Venue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("venue_id", id);
        venueService.remove(queryWrapper);
        return Result.success();
    }

}
