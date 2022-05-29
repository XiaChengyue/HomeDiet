package com.example.homediet.dto;

import com.example.homediet.entity.Diet;
import lombok.Data;

import java.util.List;

@Data
public class dietDTO {
    private List<Diet> list;
    private int pro;
    private int cho;
    private int fat;
    private int cal;

}
