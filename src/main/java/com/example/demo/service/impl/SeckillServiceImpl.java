package com.example.demo.service.impl;

import com.example.demo.dao.SeckillDao;
import com.example.demo.dao.SuccessKilledDao;
import com.example.demo.dto.Exposer;
import com.example.demo.dto.SeckillExecution;
import com.example.demo.enums.SeckillStatEnum;
import com.example.demo.exception.RepeatKillException;
import com.example.demo.exception.SeckillCloseException;
import com.example.demo.exception.SeckillException;
import com.example.demo.model.Seckill;
import com.example.demo.model.SuccessKilled;
import com.example.demo.service.SeckillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by liulq on 2018-12-11 .
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    SeckillDao seckillDao;

    @Autowired
    SuccessKilledDao successKilledDao;

    @Autowired
    private RedisTemplate redisTemplate;

    //md盐值字符串，混淆
    private final String slat = "dasdasdafafaukfh.jpi7o2o;3ip;'''''''135";

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Seckill> getSeckillList() {
        return getSeckillsByStr();
    }


    @Override
    @Transactional
    public SeckillExecution executeSeckill(Long seckillId) {

        Date nowTime = new Date();
        try {
            SuccessKilled successKilled = new SuccessKilled();
            successKilled.setSeckillId(seckillId);
            successKilled.setUserPhone(System.nanoTime());
            //记录购买行为
            int insertCount = successKilledDao.insert(successKilled);

            //减库存，todo 执行竞争条件，减库存，update获得行级锁
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新记录
                throw new SeckillCloseException("seckill is closed");
            } else {
                //秒杀成功
                return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
            }

        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            //编译异常转换，spring发现后会回滚
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    @Override
    public void initKucumToRedis() {
        List<Seckill>  seckillList = seckillDao.selectList(null);
        for(Seckill seckill : seckillList){
            String key = "kucun" + seckill.getSeckillId();
            for(int i = 0; i < seckill.getNumber(); i++){
                redisTemplate.opsForList().leftPush(key,1);
            }
        }
    }

    @Override
    public SeckillExecution executeSeckillByRedis(Long seckillId) {
        String key = "kucun" + seckillId;

        Long number =  redisTemplate.opsForList().size(key);

        if (number > 0){
            redisTemplate.opsForList().leftPop(key);
            SuccessKilled successKilled = new SuccessKilled();
            successKilled.setSeckillId(seckillId);
            successKilled.setUserPhone(System.nanoTime());
            //记录购买行为
            int insertCount = successKilledDao.insert(successKilled);
            return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
        }else {
            return new SeckillExecution(seckillId, SeckillStatEnum.END, null);
        }

    }


    private List<Seckill> getSeckillsByStr() {
        String str1 = (String) redisTemplate.opsForValue().get("seckillStr");
        List<Seckill> seckillList = null;
        try {
            seckillList = objectMapper.readValue(str1, new TypeReference<List<Seckill>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (seckillList != null) {
            return seckillList;
        } else {
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
        List<Seckill> seckillList = (List<Seckill>) redisTemplate.opsForList().leftPop("seckillList");
        if (seckillList != null) {
            return seckillList;
        } else {
            seckillList = seckillDao.selectList(null);
            redisTemplate.opsForList().leftPush("seckillList", seckillList);
            return seckillList;
        }
    }


}
