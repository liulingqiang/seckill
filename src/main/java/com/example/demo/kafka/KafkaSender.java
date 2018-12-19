package com.example.demo.kafka;

import com.example.demo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


/**
 * Created by liulq on 2018-12-19 .
 */
@Component
@Slf4j
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        kafkaTemplate.send("topic-1", "helloworld");
    }

}
