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
@TableName("seckill")
public class Seckill {
    @TableId
    private long seckillId;
    private String name;
    private int number;
    private Date startTime;
    private Date endTime;
    private Date createTime;
}
