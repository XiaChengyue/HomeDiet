package com.example.homediet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xia
 * @since 2022-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Myorder对象", description="")
public class Myorder implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "openid")
    private String openid;
    @ApiModelProperty(value = "商品id")
    private Integer itemid;
    @ApiModelProperty(value = "商品数量")
    private Integer num;
    @ApiModelProperty(value = "下单时间")
    private String time;
    @ApiModelProperty(value = "订单状态 0 已下单 1 备货中 2 送货中 3 已送单 4已取消")
    private Integer state;
    @ApiModelProperty(value = "订单编号")
    @TableField("orderNum")
    private String orderNum;


}
