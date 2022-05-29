package com.example.homediet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.common.BaseResult;
import com.example.homediet.entity.Foods;
import com.example.homediet.service.IFoodsService;
import com.example.homediet.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/foods")
public class FoodsController {

    @Autowired
    private IFoodsService foodsService;

    @GetMapping("")
    @ApiOperation("查询食物,输入为空返回全部")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称"),
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "type", value = "类型")
    })
    public BaseResult findFoods(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "id", required = false) String id,
                                @RequestParam(value = "type", required = false) String type
                                         ) {
        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (id != null) {
            queryWrapper.eq("id", id);
        }
        if (type != null) {
            queryWrapper.like("kind", type);
        }
        List<Foods> list = foodsService.list(queryWrapper);
        return BaseResult.successWithData(list);
    }

}

