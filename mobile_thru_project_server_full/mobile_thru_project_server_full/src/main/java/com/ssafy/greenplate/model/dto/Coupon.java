package com.ssafy.greenplate.model.dto;

public class Coupon {
	private Integer id;
	private String userId;
	private String type;
	private Integer discountAmount;

	public Coupon(Integer id, String userId, String type, Integer discountAmount) {
		super();
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.discountAmount = discountAmount;
	}

	public Coupon(String userId, String type, Integer discountAmount) {
		this.userId = userId;
		this.type = type;
		this.discountAmount = discountAmount;
	}

	public Coupon() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", discountAmount=" + discountAmount + ", type=" + type
				+ "]";
	}

}