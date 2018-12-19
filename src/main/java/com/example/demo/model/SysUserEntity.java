

package com.example.demo.model;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 * 
 * @author liulq
 * @date 2018年8月18日 上午9:28:55
 */
@Data
@TableName("travelrecord")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;

	private String userId;

	private Date traveldate;

	private Double fee;

	private Integer days;

}
