package com.aquent.platform.dto;

public class OrderDetailsDto {
	
	private String orderName;
	private Long   orderTime;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderName == null) ? 0 : orderName.hashCode());
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsDto other = (OrderDetailsDto) obj;
		if (orderName == null) {
			if (other.orderName != null)
				return false;
		} else if (!orderName.equals(other.orderName))
			return false;
		if (orderTime == null) {
			if (other.orderTime != null)
				return false;
		} else if (!orderTime.equals(other.orderTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderDetailsDto [orderName=" + orderName + ", orderTime=" + orderTime + "]";
	}	 
}
