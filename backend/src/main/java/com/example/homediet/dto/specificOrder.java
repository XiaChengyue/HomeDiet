package com.example.homediet.dto;

import com.example.homediet.entity.Item;
import lombok.Data;

@Data
public class specificOrder {
    private Item item;
    private int num;
}
