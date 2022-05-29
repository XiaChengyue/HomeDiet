package com.example.homediet.service.impl;

import com.example.homediet.entity.Item;
import com.example.homediet.mapper.ItemMapper;
import com.example.homediet.service.IItemService;
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
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}
