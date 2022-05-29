package com.example.homediet.service.impl;

import com.example.homediet.entity.User;
import com.example.homediet.mapper.UserMapper;
import com.example.homediet.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xia
 * @since 2022-04-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void analyze(User user) {
        int weight = Integer.parseInt(user.getWeight());
        int height = Integer.parseInt(user.getHeight());
        int age = Integer.parseInt(user.getAge());
        double frequency = Double.parseDouble(user.getFrequency());
        int bmr = 0;
        int cal = 0;
        int fat = 0;
        int cho = 0;
        int pro = 0;

         //算基础代谢率
        if(user.getGender().equals("女")){
             bmr = (int) (661+9.6*weight+1.72*height-4.7*age);
        }else if(user.getGender().equals("男")){
            bmr = (int)(670+13.73*weight+5*height-6.9*age);
        }
        user.setBmr(Integer.toString(bmr));

        //算出日常消耗代谢总量
        int tdee = (int)(bmr * frequency);
        user.setTdee(Integer.toString(tdee));

        //计算每天需要摄入的卡路里
        if(user.getGoal().equals("增肌"))
        {
            cal = (int) (tdee*1.15);
        }else if(user.getGoal().equals("减脂")){
            cal = (int) (tdee*0.9);
        }
        user.setCal(Integer.toString(cal));

        //根据卡路里计算各营养素克数（g）
        fat = (int) (cal*0.2/9);
        pro = (int) (weight*1.5);
        cho = (cal-fat*9-pro*4)/4;
        user.setFat(Integer.toString(fat));
        user.setPro(Integer.toString(pro));
        user.setCho(Integer.toString(cho));




    }
}
