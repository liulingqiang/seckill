package com.example.demo.service.impl;

import com.example.demo.dao.SeckillDao;
import com.example.demo.model.Seckill;
import com.example.demo.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liulq on 2018-12-11 .
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    SeckillDao seckillDao;

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.selectList(null);
    }
}
