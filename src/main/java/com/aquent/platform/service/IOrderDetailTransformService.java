package com.aquent.platform.service;

import java.util.List;

import com.aquent.platform.dto.OrderDetailsDto;

public interface IOrderDetailTransformService {
	
	List<OrderDetailsDto> readFileFromSource(String sourceLocation , String destinationLocation);
	Boolean writeFileToDestination(List<OrderDetailsDto> orderDetailList, String destinationLocation);

}
