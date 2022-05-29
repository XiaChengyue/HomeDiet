package com.example.homediet.dto;

import com.example.homediet.entity.Item;
import lombok.Data;

import java.util.List;
@Data
public class CartDTO {
    private Item item;
    private int num;
    private int openid;

}
