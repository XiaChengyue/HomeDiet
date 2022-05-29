package com.example.homediet.service;

import com.example.homediet.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xia
 * @since 2022-04-03
 */
public interface IUserService extends IService<User> {

    void analyze(User user);
}
