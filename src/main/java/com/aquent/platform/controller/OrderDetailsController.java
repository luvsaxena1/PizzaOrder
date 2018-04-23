package com.aquent.platform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aquent.platform.dto.OrderDetailsDto;
import com.aquent.platform.service.IOrderDetailTransformService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/order")
@Api(value = "Order Controller", description = "Pizza Order Api")
public class OrderDetailsController {

	@Value("${file.source}")
	private String sourceLocation;

	@Value("${file.destination}")
	private String destinationLocation;

	@Autowired
	private IOrderDetailTransformService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetailsController.class);

	@ApiOperation(value = "Read the order file", notes = "Read the pizza order file and convert the file into human readable format")
	@RequestMapping(method = RequestMethod.GET, value = "/convert", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderDetailsDto> convertFile() {
		LOGGER.info("OrderDetailsController.convertFile():: GET Request to get all converted list of Order Details");
		List<OrderDetailsDto> orderDetailsList = service.readFileFromSource(sourceLocation, destinationLocation);
		return orderDetailsList;
	}
}
