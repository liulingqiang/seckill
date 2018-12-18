/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.demo.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.model.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 系统用户
 * 
 * @author liulq
 * @date 2018年8月18日 上午9:34:11
 */
@Mapper
public interface SeckillDao extends BaseMapper<Seckill> {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>1，即更新了，
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
}
