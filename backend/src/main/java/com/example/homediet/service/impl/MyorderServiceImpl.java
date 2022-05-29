package com.example.homediet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.dto.myorderDTO;
import com.example.homediet.dto.specificOrder;
import com.example.homediet.entity.Item;
import com.example.homediet.entity.Myorder;
import com.example.homediet.mapper.ItemMapper;
import com.example.homediet.mapper.MyorderMapper;
import com.example.homediet.service.IMyorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xia
 * @since 2022-05-23
 */
@Service
public class MyorderServiceImpl extends ServiceImpl<MyorderMapper, Myorder> implements IMyorderService {

    @Autowired
    private MyorderMapper myorderMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Override
    public myorderDTO getdto(String orderNum) {
        myorderDTO myord = new myorderDTO();
            List<Myorder> list = myorderMapper.selectList(new QueryWrapper<Myorder>().eq("orderNum",orderNum));

            List<specificOrder> sords = new ArrayList<>();
            for(Myorder or : list){
                specificOrder sord = new specificOrder();
                sord.setNum(or.getNum());
                Item item1 = itemMapper.selectOne(new QueryWrapper<Item>().eq("id",or.getItemid()));
                sord.setItem(item1);
               sords.add(sord);
            }
            myord.setMyorders(sords);
            String openid = list.get(0).getOpenid();
            String time = list.get(0).getTime();
            int state = list.get(0).getState();
            myord.setOpenid(openid);
            myord.setOrderNum(orderNum);
            myord.setTime(time);
            myord.setState(state);
        return myord;
    }
}
