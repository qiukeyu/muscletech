package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Staff;
import com.example.demo.service.IAdvertisementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Resource
    private IAdvertisementService advertisementService;

    // 增加advertisement
    @PostMapping()
    public Result add(@RequestBody Advertisement advertisement) {
        String name = advertisement.getAdvertisementName();
        String picture = advertisement.getAdvertisementPicture();
        if (StrUtil.isBlank(name) || StrUtil.isBlank(picture)) {
            return Result.error(Constants.LACK, "lack of information");
        }
        return Result.success(advertisementService.save(advertisement));
    }

    // 修改更新
    @PostMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Advertisement advertisement) {
        return Result.success(advertisementService.updateAdvertisement(id, advertisement));
    }

    // 查询所有广告
    @GetMapping()
    public Result findAll() {
        return Result.success(advertisementService.list());
    }

    // 查看某个广告
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(advertisementService.get(id));
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Advertisement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("advertisement_id", id);
        advertisementService.remove(queryWrapper);
        return Result.success();
    }
}
