package com.example.homediet;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.entity.Diet;
import com.example.homediet.entity.Foods;
import com.example.homediet.mapper.DietMapper;
import com.example.homediet.mapper.FoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class HomeDietApplicationTests {
    @Autowired
    private DietMapper dietMapper;

    @Autowired
    private FoodsMapper foodsMapper;
    @Test
    void contextLoads() {
        int fat = 43;
        int cho = 305;
        int pro = 90;
        int cal = 1969;

        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        //30%、40%、30%
        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        //早餐
        queryWrapper.eq("kind","谷薯芋、杂豆、主食")
                .between("calorie",(double)0,(double)350)
                .between("carb",25.0,100.0);
        List<Foods> list = foodsMapper.selectList(queryWrapper);
        //生成随机数
        double a =  Math.random();
        int num = (int)(a*list.size());
        //计算能吃多少克
        Foods bre_cho= list.get(num);
        int gram = (int)((cho*0.3/list.get(num).getCarb())*100);
        //插入
        Diet bre = new Diet();
        bre.setFoodid(bre_cho.getId().toString());
        bre.setDate(format.format(date));
        bre.setGram(gram);
        bre.setTime("早餐");
        bre.setOpenid("1777");
        System.out.println(list.get(num));
        System.out.println(gram);
        dietMapper.insert(bre);


    }
    @Test
    void insertpro()
    {
        int pro = 90;
        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kind","蛋类、肉类及制品")
                .between("calorie",(double)0,(double)350)
                .between("protein",13.0,100.0)
                .between("fat",0.0,13.0);
        List<Foods> list = foodsMapper.selectList(queryWrapper);
        //生成随机数
        double a =  Math.random();
        int num = (int)(a*list.size());
        //减去碳水的蛋白质
        QueryWrapper<Diet> qw = new QueryWrapper<>();
        qw.eq("openid","1777").eq("date",format.format(date)).eq("time","早餐");
        List<Diet> dlist = dietMapper.selectList(qw);
        System.out.println(dlist);
        //计算主食蛋白质
        Foods bre_cho = foodsMapper.selectById(dlist.get(0).getFoodid());
        double pro_Of_cho = bre_cho.getProtein()/100*dlist.get(0).getGram();
        System.out.println(bre_cho);
        System.out.println(pro_Of_cho);
        Foods bre_pro= list.get(num);
        int gram = (int)(((pro*0.3-pro_Of_cho)/list.get(num).getCarb())*100);
        System.out.println(list.get(num));
        System.out.println(gram);


    }

}
