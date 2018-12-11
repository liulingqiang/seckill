

package com.example.demo.model;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 * 
 * @author liulq
 * @date 2018年8月18日 上午9:28:55
 */
@TableName("travelrecord")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;

	private String userId;

	private Date traveldate;

	private Double fee;

	private Integer days;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTraveldate() {
		return traveldate;
	}

	public void setTraveldate(Date traveldate) {
		this.traveldate = traveldate;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
}
