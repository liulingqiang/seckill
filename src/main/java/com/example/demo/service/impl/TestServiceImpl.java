package com.example.demo.service.impl;

import com.example.demo.dao.SysUserDao;
import com.example.demo.model.SysUserEntity;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by liulq on 2018-12-05 .
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    SysUserDao sysUserDao;

    @Override
    public void testRead() {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for(int i = 0; i < 1001; i++) {
            SysUserEntity sysUserEntity = sysUserDao.selectById(1);
            if(sysUserEntity.getUserId().equals("10.201.60.107")){
                count1++;
            }
            if(sysUserEntity.getUserId().equals("localhost.kubeadm.node1")){
                count2++;
            }
            if(sysUserEntity.getUserId().equals("localhost.kubeadm.node2")){
                count3++;
            }
        }
        System.out.println("10.201.60.107 count: " + count1);
        System.out.println("localhost.kubeadm.node1 count: " + count2);
        System.out.println("localhost.kubeadm.node2 count: " + count3);
    }

    @Override
    public void testWrite() {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(new Random().nextLong());
        sysUserEntity.setUserId("liulq");
        sysUserDao.insert(sysUserEntity);

    }
}
