package com.example.demo.service.impl;

import com.example.demo.model.Seckill;
import com.example.demo.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by liulq on 2018-12-11 .
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceImplTest {
    @Autowired
    SeckillService seckillService;

    @Test
    public void listAllProduces()  {
        List<Seckill> seckillList = seckillService.getSeckillList();
        System.out.println(seckillList.size());
        for(Seckill seckill : seckillList){
            System.out.println(seckill.toString());
        }
    }

}