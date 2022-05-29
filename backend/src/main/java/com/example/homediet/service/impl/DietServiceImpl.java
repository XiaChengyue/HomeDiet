package com.example.homediet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.homediet.entity.Diet;
import com.example.homediet.entity.Foods;
import com.example.homediet.entity.User;
import com.example.homediet.mapper.DietMapper;
import com.example.homediet.mapper.FoodsMapper;
import com.example.homediet.mapper.UserMapper;
import com.example.homediet.service.IDietService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xia
 * @since 2022-04-17
 */
@Service
public class DietServiceImpl extends ServiceImpl<DietMapper, Diet> implements IDietService {

    @Autowired
    private DietMapper dietMapper;

    @Autowired
    private FoodsMapper foodsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void updatediet(String openid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        List<User> users = userMapper.selectList(queryWrapper);
        for(User u : users){
            updateOneDiet(u);
        }
        System.out.println("update finish");

    }

    public void updateOneDiet(User user){
        int fat = Integer.parseInt(user.getFat());
        int cho = Integer.parseInt(user.getCho());
        int pro = Integer.parseInt(user.getPro());
        String openid = user.getOpenid();
        //更新早餐
        insertcho(cho,openid,1);
        insertpro(pro,openid,1);
        insertfat(fat,openid,1);
        insertveg(openid,1);
        //更新午餐
        insertcho(cho,openid,2);
        insertpro(pro,openid,2);
        insertfat(fat,openid,2);
        insertveg(openid,2);
        //更新晚餐
        insertcho(cho,openid,3);
        insertpro(pro,openid,3);
        insertfat(fat,openid,3);
        insertveg(openid,3);
    }

    public void insertcho(int cho,String openid,int time){
        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        //30%、40%、30%
        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        //获取食物列表
        queryWrapper.eq("kind","谷薯芋、杂豆、主食")
                .eq("type",1)
                .between("calorie",(double)0,(double)350)
                .between("carb",25.0,100.0);
        List<Foods> list = foodsMapper.selectList(queryWrapper);
        //生成随机数
        double a =  Math.random();
        int num = (int)(a*list.size());
        //计算能吃多少克
        Foods bre_cho= list.get(num);
        int gram = 0;
        if(time==1||time==3) {
            gram = (int) ((cho * 0.3 / list.get(num).getCarb()) * 100);
        }else {
            gram = (int) ((cho * 0.4 / list.get(num).getCarb()) * 100);
        }

        //插入
        Diet bre = new Diet();
        bre.setFoodid(bre_cho.getId().toString());
        bre.setDate(format.format(date));
        bre.setGram(gram);
        if(time==1) {
            bre.setTime("早餐");
        }else if(time==2) {
            bre.setTime("午餐");
        }else{
            bre.setTime("晚餐");
        }
        bre.setOpenid(openid);
        dietMapper.insert(bre);
    }

    public void insertpro(int pro,String openid,int time){
        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kind","蛋类、肉类及制品")
                .eq("type",1)
                .between("calorie",(double)0,(double)350)
                .between("protein",13.0,100.0)
                .between("fat",0.0,13.0);
        List<Foods> list = foodsMapper.selectList(queryWrapper);
        //生成随机数
        double a =  Math.random();
        int num = (int)(a*list.size());
        //减去碳水的蛋白质
        QueryWrapper<Diet> qw = new QueryWrapper<>();
        if(time==1){
            qw.eq("openid",openid).eq("date",format.format(date)).eq("time","早餐");
        }else if(time==2){
            qw.eq("openid",openid).eq("date",format.format(date)).eq("time","午餐");
        }else{
            qw.eq("openid",openid).eq("date",format.format(date)).eq("time","晚餐");
        }

        List<Diet> dlist = dietMapper.selectList(qw);
        System.out.println(dlist);
        //计算主食蛋白质
        Foods bre_cho = foodsMapper.selectById(dlist.get(0).getFoodid());
        double pro_Of_cho = bre_cho.getProtein()/100*dlist.get(0).getGram();

        Foods bre_pro= list.get(num);
        int gram = 0;
        if(time==1||time==3) {
            gram = (int)(((pro*0.3-pro_Of_cho)/list.get(num).getProtein())*100);
        }else {
            gram = (int)(((pro*0.4-pro_Of_cho)/list.get(num).getProtein())*100);
        }
        //插入
        Diet bre = new Diet();
        bre.setFoodid(list.get(num).getId().toString());
        bre.setGram(gram);
        if(time==1) {
            bre.setTime("早餐");
        }else if(time==2) {
            bre.setTime("午餐");
        }else{
            bre.setTime("晚餐");
        }
        bre.setOpenid(openid);
        bre.setDate(format.format(date));
        dietMapper.insert(bre);
    }
    public void insertfat(int fat,String openid,int time){
        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kind","食用油、油脂及制品")
                .eq("type",1);
        List<Foods> list = foodsMapper.selectList(queryWrapper);
        //生成随机数
        double a =  Math.random();
        int num = (int)(a*list.size());

        //获取早餐中碳水、蛋白质的脂肪含量
        QueryWrapper<Diet> qw = new QueryWrapper<>();
        if(time==1){
            qw.eq("openid",openid).eq("date",format.format(date)).eq("time","早餐");
        }else if(time==2){
            qw.eq("openid",openid).eq("date",format.format(date)).eq("time","午餐");
        }else{
            qw.eq("openid",openid).eq("date",format.format(date)).eq("time","晚餐");
        }
        List<Diet> dlist = dietMapper.selectList(qw);
        Foods bre_cho = foodsMapper.selectById(dlist.get(0).getFoodid());
        Foods bre_pro = foodsMapper.selectById(dlist.get(1).getFoodid());
        double fat_of_bre =  bre_cho.getFat()+bre_pro.getFat();

        Foods bre_fat= list.get(num);
        int gram = 0;
        if(time==1||time==3) {
            gram = (int)(((fat*0.3-fat_of_bre)/list.get(num).getFat())*100);
        }else{
            gram = (int)(((fat*0.4-fat_of_bre)/list.get(num).getFat())*100);
        }


        //插入
        Diet bre = new Diet();
        bre.setFoodid(bre_fat.getId().toString());
        bre.setGram(gram);
        if(time==1) {
            bre.setTime("早餐");
        }else if(time==2) {
            bre.setTime("午餐");
        }else{
            bre.setTime("晚餐");
        }
        bre.setOpenid(openid);
        bre.setDate(format.format(date));
        dietMapper.insert(bre);
    }

    public void insertveg(String openid,int time){
        //获取今日日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kind","蔬果和菌藻")
                .eq("type",1);
        List<Foods> list = foodsMapper.selectList(queryWrapper);
        //生成随机数
        double a =  Math.random();
        int num = (int)(a*list.size());

        Foods bre_cho= list.get(num);
        //插入
        Diet bre = new Diet();
        bre.setFoodid(bre_cho.getId().toString());
        bre.setGram(100);
        if(time==1) {
            bre.setTime("早餐");
        }else if(time==2) {
            bre.setTime("午餐");
        }else{
            bre.setTime("晚餐");
        }
        bre.setOpenid(openid);
        bre.setDate(format.format(date));
        dietMapper.insert(bre);

    }
}
