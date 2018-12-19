package com.example.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by liulq on 2018-12-19 .
 */
@Data
public class Message {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳
}
