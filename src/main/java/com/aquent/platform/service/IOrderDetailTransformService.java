package com.aquent.platform.service;

import java.util.List;

import com.aquent.platform.dto.OrderDetailsDto;

public interface IOrderDetailTransformService {
	
	Boolean readFileFromSource();
	Boolean writeFileToDestination(List<OrderDetailsDto> orderDetailList);

}
