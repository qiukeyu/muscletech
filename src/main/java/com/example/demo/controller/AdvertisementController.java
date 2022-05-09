package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Staff;
import com.example.demo.service.IAdvertisementService;
import com.example.demo.utils.UploadImgBed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Resource
    private IAdvertisementService advertisementService;

    // 增加advertisement
    @PostMapping()
    public Result add(@RequestBody Advertisement advertisement, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        String name = advertisement.getAdvertisementName();
        String picture = advertisement.getAdvertisementPicture();
        String originalFilename = multipartFile.getOriginalFilename();
        if (StrUtil.isBlank(name) || StrUtil.isBlank(picture) || StrUtil.isBlank(originalFilename)) {
            return Result.error(Constants.LACK, "lack of information");
        }

        String targetURL = UploadImgBed.createUploadFileUrl(0, name, originalFilename);

        Map<String, Object> uploadBodyMap = UploadImgBed.getUploadBodyMap(multipartFile.getBytes());
        String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
        JSONObject jsonObj = JSONUtil.parseObj(JSONResult);
        if(jsonObj == null || jsonObj.getObj("commit") == null){
            return Result.error(Constants.ERROR);
        }
        JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));
        advertisementService.save(advertisement);
        return Result.success(content.getObj("download_url"));
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
