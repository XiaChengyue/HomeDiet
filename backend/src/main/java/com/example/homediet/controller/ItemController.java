package com.example.homediet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.common.BaseResult;
import com.example.homediet.entity.Foods;
import com.example.homediet.entity.Item;
import com.example.homediet.service.IItemService;
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
 * @since 2022-05-23
 */
@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    IItemService itemService;

    @GetMapping("")
    @ApiOperation("查询商品，为空返回所有")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称"),
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "type", value = "类型"),
    })
    public BaseResult findItem(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "id", required = false) String id,
                                @RequestParam(value = "type", required = false) String type
    ) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (id != null) {
            queryWrapper.eq("id", id);
        }
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        List<Item> list = itemService.list(queryWrapper);
        return BaseResult.successWithData(list);
    }

}

