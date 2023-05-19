package com.ssafy.greenplate.model.dto;

public class OrderDetail {
	private Integer id;
	private Integer orderId;
	private Integer productId;
	private Integer dressingId;
	private String addedStuff;
	private Integer quantity;

	public OrderDetail(Integer id, Integer orderId, Integer productId, Integer dressingId,
			String addedStuff, Integer quantity) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.dressingId = dressingId;
		this.addedStuff = addedStuff;
		this.quantity = quantity;
	}

	public OrderDetail(Integer productId, Integer dressingId,
			String addedStuff, Integer quantity) {
		this.productId = productId;
		this.dressingId = dressingId;
		this.addedStuff = addedStuff;
		this.quantity = quantity;
	}

	public OrderDetail(Integer orderId, Integer productId, Integer dressingId,
			String addedStuff, Integer quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.dressingId = dressingId;
		this.addedStuff = addedStuff;
		this.quantity = quantity;
	}

	public OrderDetail() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getDressingId() {
		return dressingId;
	}

	public void setDressingId(Integer dressingId) {
		this.dressingId = dressingId;
	}

	public String getAddedStuff() {
		return addedStuff;
	}

	public void setAddedStuff(String addedStuff) {
		this.addedStuff = addedStuff;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", dressingId="
				+ dressingId + ", addedStuff=" + addedStuff + ", quantity=" + quantity
				+ "]";
	}

}
