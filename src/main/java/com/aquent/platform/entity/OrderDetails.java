package com.aquent.platform.entity;

public class OrderDetails {

	private String orderName;
	private Long orderTime;

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Long orderTime) {
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderName=" + orderName + ", orderTime=" + orderTime + "]";
	}

}
