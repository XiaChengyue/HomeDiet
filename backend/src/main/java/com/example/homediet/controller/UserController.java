package com.example.homediet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.common.BaseResult;
import com.example.homediet.common.ErrorCode;
import com.example.homediet.entity.User;
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
 * @since 2022-04-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    @ApiOperation("开启减脂之旅,不用填fat pro cho,返回分析内容")
    public BaseResult register(@RequestBody User user) {
        //判断是否已经注册
        User dbUser = userService.getOne(
                new QueryWrapper<User>().eq("openid", user.getOpenid()));
        if (dbUser != null) {
            return BaseResult.failWithErrorCode(ErrorCode.REGISTER_FAILED);
        }
        //分析并返回
        userService.analyze(user);
        if (userService.save(user)) {
            return BaseResult.success();
        } else {
            return BaseResult.failWithErrorCode(ErrorCode.REGISTER_FAILED);
        }
    }


    @PutMapping("/update")
    @ApiOperation("更新身体数据,需要获取到user在数据库中的id")
    public BaseResult update(@RequestBody User user) {
        User dbinfo = userService.getById(user.getId());
        //进行分析
        userService.analyze(user);
        if(dbinfo!=null&&userService.updateById(user))
        {
            return BaseResult.success();
        }else{
            return BaseResult.failWithErrorCode(ErrorCode.INSERT_FAILED);
        }
    }

    @GetMapping("")
    @ApiOperation("查询用户信息，输入为空返回所有")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid"),
    })
    public BaseResult findUser(@RequestParam(value = "openid", required = false) String openid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (openid != null) {
            queryWrapper.eq("openid", openid);
        }
        List<User> list = userService.list(queryWrapper);
        return BaseResult.successWithData(list);
    }
}


