package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.Card;
import com.example.demo.entity.Staff;
import com.example.demo.service.ICardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/card")
public class CardController {

    @Resource
    private ICardService cardService;

    // 添加银行卡,注意Card类里的userid是必需的,即当前用户的id
    @PostMapping()
    public Result add(@RequestBody Card card) {
        String name = card.getCardName();
        String number = card.getCardNumber();
        String phone = card.getCardPhone();
        if (StrUtil.isBlank(name) || StrUtil.isBlank(number) || StrUtil.isBlank(phone)) {
            return Result.error(Constants.LACK, "lack of information");
        }
        return Result.success(cardService.save(card));
    }

    // 没有修改银行卡信息的功能，一般银行卡信息没有修改的必要

    // 当前用户的所有银行卡
    @GetMapping()
    public Result findAll() {
        return Result.success(cardService.findAll());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(cardService.get(id));
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Card> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_id", id);
        cardService.remove(queryWrapper);
        return Result.success();
    }

}
