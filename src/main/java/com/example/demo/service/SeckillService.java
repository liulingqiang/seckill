package com.example.demo.service;

import com.example.demo.dto.SeckillExecution;
import com.example.demo.model.Seckill;

import java.util.List;

/**
 * Created by liulq on 2018-12-11 .
 */
public interface SeckillService {
    List<Seckill> getSeckillList();


    SeckillExecution executeSeckill(Long seckillId);

    void initKucumToRedis();

    SeckillExecution executeSeckillByRedis(Long seckillId);
}
