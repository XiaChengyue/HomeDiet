package com.example.homediet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Diet对象", description="")
public class Diet implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "openid")
    private String openid;
    @ApiModelProperty(value = "食物id")
    private String foodid;
    @ApiModelProperty(value = "日期")
    private String date;
    @ApiModelProperty(value = "时间，早餐  午餐 晚餐")
    private String time;
    @ApiModelProperty(value = "克数")
    private Integer gram;

    @TableField(exist = false)
    private Foods food;


}
