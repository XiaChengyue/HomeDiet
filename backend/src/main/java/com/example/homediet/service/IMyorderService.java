package com.example.homediet.service;

import com.example.homediet.dto.myorderDTO;
import com.example.homediet.entity.Myorder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xia
 * @since 2022-05-23
 */
public interface IMyorderService extends IService<Myorder> {
    public myorderDTO getdto(String orderNum);

}
