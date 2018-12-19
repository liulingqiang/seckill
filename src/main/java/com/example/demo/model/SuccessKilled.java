package com.example.demo.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 系统用户
 *
 * @author liulq
 * @date 2018年8月18日 上午9:28:55
 */
@Data
@TableName("success_killed")
public class SuccessKilled {
    @TableId
    private long seckillId;
    private long userPhone;
    private short state;
    private Date createTime;

    //变通
    //复合属性,多对一
    private Seckill seckill;


}
