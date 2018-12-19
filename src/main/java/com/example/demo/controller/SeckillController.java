package com.example.demo.controller;

import com.example.demo.dto.AjaxResult;
import com.example.demo.dto.SeckillExecution;
import com.example.demo.exception.SeckillCloseException;
import com.example.demo.kafka.KafkaSender;
import com.example.demo.model.Seckill;
import com.example.demo.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liulq on 2018-12-11 .
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private KafkaSender kafkaSender;

    //商品列表
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public AjaxResult list(Model model){
        List<Seckill> list=seckillService.getSeckillList();
        return AjaxResult.ok(list);
    }

    //执行秒杀
    @RequestMapping(value = "/execution",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult execute(){
        Integer a = 1000;
        Long seckillId = a.longValue();
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId);
            return AjaxResult.ok(execution);
        }catch (SeckillCloseException e){
            return AjaxResult.error("seckill closed!");
        }catch (Exception e){
            return AjaxResult.error("seckill Exception!");
        }
    }

    //商品列表
    @RequestMapping(value="/initKucumToRedis",method = RequestMethod.GET)
    public AjaxResult initKucumToRedis(){
        seckillService.initKucumToRedis();
        return AjaxResult.ok();
    }

    //商品列表
    @RequestMapping(value="/executeSeckillByRedis",method = RequestMethod.GET)
    public AjaxResult executeSeckillByRedis(){
        Integer a = 1000;
        Long seckillId = a.longValue();
        SeckillExecution execution = seckillService.executeSeckillByRedis(seckillId);
        return AjaxResult.ok(execution);
    }

    //商品列表
    @RequestMapping(value="/kafkaSender",method = RequestMethod.GET)
    public AjaxResult kafkaSender(){

        kafkaSender.send();
        return AjaxResult.ok();
    }



}
