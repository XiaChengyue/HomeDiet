package com.example.homediet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.common.BaseResult;
import com.example.homediet.common.ErrorCode;
import com.example.homediet.dto.CartDTO;
import com.example.homediet.dto.myorderDTO;
import com.example.homediet.dto.postOrderDTO;
import com.example.homediet.dto.specificOrder;
import com.example.homediet.entity.Cart;
import com.example.homediet.entity.Item;
import com.example.homediet.entity.Myorder;
import com.example.homediet.service.IItemService;
import com.example.homediet.service.IMyorderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

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
@RequestMapping("/myorder")
public class MyorderController {
    @Autowired
    IMyorderService myorderService;
    @Autowired
    IItemService itemService;
    @PostMapping("/add")
    @ApiOperation("下单，time state orderNum不用写")
    public BaseResult addOrder(@RequestBody postOrderDTO pdto) {
        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
        //转换成order插入
        Myorder or = new Myorder();
        for(Myorder item : pdto.getMyOrders()){
            or.setItemid(item.getItemid());
            or.setNum(item.getNum());
            or.setOpenid(pdto.getOpenid());
            or.setTime(format.format(date));
            or.setOrderNum(format2.format(date)+pdto.getOpenid());
            or.setState(0);
            if(!myorderService.save(or)){
                return BaseResult.failWithErrorCode(ErrorCode.SYSTEM_FAILED);
            }
        }
        return BaseResult.success();
    }

    @GetMapping("/cancel")
    @ApiOperation("取消订单")
    public BaseResult cancelOrder(@RequestParam(value = "orderNum", required = true) String orderNum) {

        QueryWrapper<Myorder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderNum",orderNum);
        List<Myorder> list=myorderService.list(queryWrapper);
        if(list.isEmpty()){
            return BaseResult.failWithErrorCode(ErrorCode.DATA_NOT_FOUND);
        }
        for(Myorder item:list){
            item.setState(4);
            if(!myorderService.updateById(item)){
                return BaseResult.failWithErrorCode(ErrorCode.SYSTEM_FAILED);
            }
        }
            return BaseResult.success();
    }

    @GetMapping("")
    @ApiOperation("返回订单内容,输入为空返回所有人的订单,openid和orderNum可以只填一个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid"),
            @ApiImplicitParam(name = "orderNum", value = "订单号")
    })
    public BaseResult getOrder(@RequestParam(value = "openid", required = false) String openid,@RequestParam(value = "orderNum", required = false) String orderNum
    ) {
        QueryWrapper<Myorder> queryWrapper = new QueryWrapper<>();
        if (openid != null) {
            queryWrapper.eq("openid",openid);
        }
        if (orderNum != null) {
            queryWrapper.eq("orderNum",orderNum);
        }
        List<Myorder> list = myorderService.list(queryWrapper);
        Set<String> orderNumSet = new HashSet<>();
        for (Myorder order : list){
            if(!orderNumSet.contains(order.getOrderNum())){
                orderNumSet.add(order.getOrderNum());
            }
        }
        //开始转换
        List<myorderDTO> orders = new ArrayList<>();
        for(String ordernum : orderNumSet){
            myorderDTO or = myorderService.getdto(ordernum);
            orders.add(or);
        }
        return BaseResult.successWithData(orders);
    }

}

