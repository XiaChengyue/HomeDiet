package com.example.homediet.dto;


import com.example.homediet.entity.Myorder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="下订单", description="")
public class postOrderDTO {
   private List<Myorder> myOrders;//只用传入itemid 和 num
   @ApiModelProperty(value = "openid")
   private String openid;
   @ApiModelProperty(value = "商品数量")
   private int orderNum;
   @ApiModelProperty(value = "下单时间")
   private String time;

}
