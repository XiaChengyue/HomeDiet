package com.example.homediet.dto;

import lombok.Data;

import java.util.List;

@Data
public class myorderDTO {
    private String time;
    private String openid;
    private int state;
    private String orderNum;
    private List<specificOrder> myorders;

}
