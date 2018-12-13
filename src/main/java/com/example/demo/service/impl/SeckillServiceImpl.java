package com.example.demo.service.impl;

import com.example.demo.dao.SeckillDao;
import com.example.demo.model.Seckill;
import com.example.demo.service.SeckillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by liulq on 2018-12-11 .
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    SeckillDao seckillDao;

    @Autowired
    private RedisTemplate redisTemplate;

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Seckill> getSeckillList() {
        return getSeckillsByStr();
    }
    private List<Seckill> getSeckillsByStr() {
        String str1 = (String)redisTemplate.opsForValue().get("seckillStr");
        List<Seckill> seckillList = null;
        try {
            seckillList = objectMapper.readValue(str1, new TypeReference<List<Seckill>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(seckillList != null) {
            return seckillList;
        }else {
            seckillList = seckillDao.selectList(null);
            String jsonlist = null;
            try {
                 jsonlist = objectMapper.writeValueAsString(seckillList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            redisTemplate.opsForValue().set("seckillStr", jsonlist);
            return seckillList;
        }
    }

    private List<Seckill> getSeckillsByList() {
        List<Seckill> seckillList = (List<Seckill>)redisTemplate.opsForList().leftPop("seckillList");
        if(seckillList != null) {
           return seckillList;
        }else {
            seckillList = seckillDao.selectList(null);
            redisTemplate.opsForList().leftPush("seckillList", seckillList);
            return seckillList;
        }
    }


}
