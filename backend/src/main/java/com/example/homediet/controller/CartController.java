package com.example.homediet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.common.BaseResult;
import com.example.homediet.common.ErrorCode;
import com.example.homediet.dto.CartDTO;
import com.example.homediet.entity.*;
import com.example.homediet.service.ICartService;
import com.example.homediet.service.IItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/cart")
@Api(value = "Cart Controller", description = "Cart Controller")
public class CartController {
    @Autowired
    ICartService cartService;
    @Autowired
    IItemService itemService;
    @PostMapping("/add")
    @ApiOperation("首次添加，数量默认为1，id不用填，num不用填")
    public BaseResult addCart(@RequestBody Cart cart) {
        //判断是否已经存在
        Cart dbCart = cartService.getOne(
                new QueryWrapper<Cart>().eq("openid",cart.getOpenid()).eq("itemid",cart.getItemid()));
        if (dbCart != null) {
            int num = dbCart.getNum();
            dbCart.setNum(++num);
            cartService.updateById(dbCart);
            return BaseResult.success();
        }else{
            //不存在，自动创建
            cart.setNum(1);
            if (cartService.save(cart)) {
                return BaseResult.success();
            } else {
                return BaseResult.failWithErrorCode(ErrorCode.INSERT_FAILED);
            }
        }
    }
    @PostMapping("/sub")
    @ApiOperation("购物车数量--，id不用填，num不用填")
    public BaseResult subCart(@RequestBody Cart cart) {
        //判断是否已经存在
        Cart dbCart = cartService.getOne(
                new QueryWrapper<Cart>().eq("openid",cart.getOpenid()).eq("itemid",cart.getItemid()));
        if (dbCart != null) {
            int num = dbCart.getNum();
            if(num>1){
            dbCart.setNum(--num);
            cartService.updateById(dbCart);
            return BaseResult.success();}
            else if(num==0){
                return BaseResult.failWithErrorCode(ErrorCode.UPDATE_FAILED);
            }else if(num==1){
                cartService.removeById(dbCart.getId());
                return BaseResult.success();
            }
        }else{
            //不存在，不能修改
            return BaseResult.failWithErrorCode(ErrorCode.CART_NOT_FOUNT);
        }
        return BaseResult.failWithErrorCode(ErrorCode.SYSTEM_FAILED);

    }

    @GetMapping("")
    @ApiOperation("返回购物车内容，openid为空时返回所有用户购物车内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid")
    })
    public BaseResult getCart(@RequestParam(value = "openid", required = false) String openid
    ) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        if (openid != null) {
            queryWrapper.eq("openid", openid);
        }
        List<Cart> list = cartService.list(queryWrapper);

        List<CartDTO> ca = new ArrayList<CartDTO>();

        for(Cart item : list){
            QueryWrapper<Item> q = new QueryWrapper<>();
            q.eq("id",item.getItemid());
            Item item1 = itemService.getOne(q);
            CartDTO cadto = new CartDTO();
            cadto.setNum(item.getNum());
            cadto.setItem(item1);
            cadto.setOpenid(item.getOpenid());
            ca.add(cadto);
        }
        return BaseResult.successWithData(ca);
    }

}

