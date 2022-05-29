package com.example.homediet.service;

import com.example.homediet.entity.Diet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.homediet.entity.User;

import javax.annotation.PostConstruct;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xia
 * @since 2022-04-17
 */
public interface IDietService extends IService<Diet> {


    public void updatediet(String openid);



}