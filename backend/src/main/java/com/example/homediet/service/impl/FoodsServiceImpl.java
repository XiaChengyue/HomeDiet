package com.example.homediet.service.impl;

import com.example.homediet.entity.Foods;
import com.example.homediet.mapper.FoodsMapper;
import com.example.homediet.service.IFoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xia
 * @since 2022-04-17
 */
@Service
public class FoodsServiceImpl extends ServiceImpl<FoodsMapper, Foods> implements IFoodsService {

}
