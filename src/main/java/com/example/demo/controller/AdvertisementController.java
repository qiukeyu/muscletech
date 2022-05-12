package com.example.demo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.Advertisement;
import com.example.demo.service.IAdvertisementService;
import com.example.demo.utils.TokenUtils;
import com.example.demo.utils.UploadImgBed;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Resource
    private IAdvertisementService advertisementService;

    @PostMapping()
    @ApiOperation(value = "添加广告", notes = "name\nfile")
    public Result add(@RequestParam("name") String name, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementName(name);
        String originalFilename = multipartFile.getOriginalFilename();
        String targetURL = UploadImgBed.createUploadFileUrl(0, originalFilename);
        if (StrUtil.isBlank(name) || StrUtil.isBlank(originalFilename)) {
            return Result.error(Constants.LACK, "lack of information");
        }

        Map<String, Object> uploadBodyMap = UploadImgBed.getUploadBodyMap(multipartFile.getBytes());
        String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
        JSONObject jsonObj = JSONUtil.parseObj(JSONResult);
        if (jsonObj.getObj("commit") == null) {
            return Result.error(Constants.ERROR, "can't upload to ImageBed");
        }
        JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));

        advertisement.setAdvertisementPicture(content.getObj("download_url").toString());
        advertisement.setCenterId(TokenUtils.getCurrentStaff().getCenterId());

        DateTime dateTime = new DateTime();
        advertisement.setDatetime(dateTime.toString("yyyy-MM-dd hh:mm:ss"));

        return Result.success(advertisementService.save(advertisement));
    }


    @PostMapping("/{id}")
    @ApiOperation(value = "修改广告", notes = "id\nname（内容可以为空）\nfile（内容可以为空）")
    public Result update(@PathVariable Integer id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws IOException {
        Advertisement advertisement = new Advertisement();
        if (name != null)
            advertisement.setAdvertisementName(name);
        if (multipartFile != null) {
            String originalFilename = multipartFile.getOriginalFilename();
            String targetURL = UploadImgBed.createUploadFileUrl(0, originalFilename);
            Map<String, Object> uploadBodyMap = UploadImgBed.getUploadBodyMap(multipartFile.getBytes());
            String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
            JSONObject jsonObj = JSONUtil.parseObj(JSONResult);
            if (jsonObj.getObj("commit") == null) {
                return Result.error(Constants.ERROR);
            }
            JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));
            advertisement.setAdvertisementPicture(content.getObj("download_url").toString());
        }
        return Result.success(advertisementService.updateAdvertisement(id, advertisement));
    }

    @ApiOperation(value = "获取所有广告", notes = "")
    @GetMapping()
    public Result findAll() {
        return Result.success(advertisementService.list());
    }

    @ApiOperation(value = "获取某一广告", notes = "id")
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(advertisementService.get(id));
    }

    @ApiOperation(value = "删除某一广告", notes = "id")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Advertisement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("advertisement_id", id);
        advertisementService.remove(queryWrapper);
        return Result.success();
    }
}
