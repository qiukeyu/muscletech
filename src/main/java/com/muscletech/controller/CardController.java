package com.muscletech.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muscletech.common.Constants;
import com.muscletech.common.Result;
import com.muscletech.entity.Card;
import com.muscletech.service.ICardService;
import com.muscletech.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/card")
public class CardController {

    @Resource
    private ICardService cardService;


    @PostMapping()
    @ApiOperation(value = "添加银行卡", notes = "card.cardName\ncard.cardNumber\ncard.cardPhone")
    public Result add(@RequestBody Card card) {
        String name = card.getCardName();
        String number = card.getCardNumber();
        String phone = card.getCardPhone();
        if (StrUtil.isBlank(name) || StrUtil.isBlank(number) || StrUtil.isBlank(phone)) {
            return Result.error(Constants.LACK, "lack of information");
        }
        card.setUserId(Objects.requireNonNull(TokenUtils.getCurrentUser()).getUserId());
        return Result.success(cardService.save(card));
    }


    @GetMapping()
    @ApiOperation(value = "当前用户的所有银行卡", notes = "")
    public Result findAll() {
        return Result.success(cardService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "某张银行卡", notes = "id")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(cardService.get(id));
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除银行卡", notes = "id")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Card> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_id", id);
        cardService.remove(queryWrapper);
        return Result.success();
    }

}
