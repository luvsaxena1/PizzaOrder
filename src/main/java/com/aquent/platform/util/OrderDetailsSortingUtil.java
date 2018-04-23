package com.aquent.platform.util;

import java.util.Comparator;

import com.aquent.platform.dto.OrderDetailsDto;

public class OrderDetailsSortingUtil implements Comparator<OrderDetailsDto>{

	@Override
	public int compare(OrderDetailsDto o1, OrderDetailsDto o2){
			
		if(o1.getOrderTime() != o2.getOrderTime()){
			return (int) (o1.getOrderTime()- o2.getOrderTime());
		}
		return o1.getOrderName().compareTo(o2.getOrderName());
	}
}
