package com.example.homediet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.common.BaseResult;
import com.example.homediet.dto.CartDTO;
import com.example.homediet.dto.dietDTO;
import com.example.homediet.entity.*;
import com.example.homediet.service.IDietService;
import com.example.homediet.service.IFoodsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xia
 * @since 2022-04-17
 */
@CrossOrigin
@RestController
@RequestMapping("/diet")
public class DietController {
    @Autowired
    private IDietService dietService;
    @Autowired
    private IFoodsService foodsService;


    @GetMapping("")
    @ApiOperation("返回食谱评估")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid")
    })
    public BaseResult analyzediet(@RequestParam(value = "openid", required = true) String openid
    ) {
        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        int pro=0;
        int fat=0;
        int cho=0;
        int cal=0;
        QueryWrapper<Diet> queryWrapper = new QueryWrapper<>();
        if (openid != null) {
            queryWrapper.eq("openid", openid).eq("date",format.format(date));
        }
        List<Diet> list = dietService.list(queryWrapper);
        if(list.isEmpty()){
            dietService.updatediet(openid);
        }
        queryWrapper.eq("openid", openid).eq("date",format.format(date));
        list = dietService.list(queryWrapper);
        for(Diet item : list){
            QueryWrapper<Foods> q = new QueryWrapper<>();
            q.eq("id",item.getFoodid());
            Foods food = foodsService.getOne(q);
            item.setFood(food);
            pro += food.getProtein()/100*item.getGram();
            fat += food.getFat()/100*item.getGram();
            cho += food.getCarb()/100*item.getGram();
            cal += food.getCalorie()/100*item.getGram();
        }
        dietDTO dietdto = new dietDTO();
        dietdto.setList(list);
        dietdto.setCal(cal);
        dietdto.setCho(cho);
        dietdto.setFat(fat);
        dietdto.setPro(pro);
        return BaseResult.successWithData(dietdto);
    }


}

