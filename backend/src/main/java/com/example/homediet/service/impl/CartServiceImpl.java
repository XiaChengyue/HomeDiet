package com.example.homediet.service.impl;

import com.example.homediet.entity.Cart;
import com.example.homediet.mapper.CartMapper;
import com.example.homediet.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xia
 * @since 2022-05-23
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
