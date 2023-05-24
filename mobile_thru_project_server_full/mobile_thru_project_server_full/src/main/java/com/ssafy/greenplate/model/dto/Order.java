package com.ssafy.greenplate.model.dto;

import java.util.Date;
import java.util.List;

public class Order {
	private Integer id;
	private String userId;
	private String orderTable;
	private Date orderTime;
	private Character completed;
	private String storeName;
	private Integer discountAmount;
	private String payType;
	private Integer totalOrderPrice;
	private List<OrderDetail> details;

	public Order(Integer id, String userId, String orderTable, Date orderTime, Character complited, String storeName,
			Integer discountAmount, String payType, Integer totalOrderPrice) {
		this.id = id;
		this.userId = userId;
		this.orderTable = orderTable;
		this.orderTime = orderTime;
		this.completed = complited;
		this.storeName = storeName;
		this.discountAmount = discountAmount;
		this.payType = payType;
		this.totalOrderPrice = totalOrderPrice;
	}

	public Order(String userId, String orderTable, Date orderTime, Character complited, String storeName,
			Integer discountAmount, String payType, Integer totalOrderPrice) {
		this.userId = userId;
		this.orderTable = orderTable;
		this.orderTime = orderTime;
		this.completed = complited;
		this.storeName = storeName;
		this.discountAmount = discountAmount;
		this.payType = payType;
		this.totalOrderPrice = totalOrderPrice;
		
	}

	public Order() {
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

	public String getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(String orderTable) {
		this.orderTable = orderTable;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Character getCompleted() {
		return completed;
	}

	public void setCompleted(Character completed) {
		this.completed = completed;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderedPrice(Integer totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", orderTable=" + orderTable + ", orderTime=" + orderTime
				+ ", completed=" + completed + ", storeName=" + storeName + ", discountAmount=" + discountAmount
				+ ", payType=" + payType + ", details=" + details + "]";
	}

}
