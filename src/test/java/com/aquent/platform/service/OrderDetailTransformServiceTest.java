package com.aquent.platform.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.aquent.platform.dto.OrderDetailsDto;
import com.aquent.platform.service.impl.OrderDetailTransformService;

@RunWith(SpringRunner.class)
public class OrderDetailTransformServiceTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testReadFileFromSource_whenSourceLocationIsNotCorrect() throws Exception  {
		OrderDetailTransformService detailTransformService = new OrderDetailTransformService();
		String sourceLocation = "";
		String destinationLocation="C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_sorted.txt";
		List<OrderDetailsDto> list = detailTransformService.readFileFromSource(sourceLocation,destinationLocation);
		assertThat(list).isEmpty();
	}
	
	@Test
	public void testReadFileFromSource_whenSourceFileIsNotCorrect() throws Exception  {
		OrderDetailTransformService detailTransformService = new OrderDetailTransformService();
		String sourceLocation = "C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_ordered1.txt";
		String destinationLocation="C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_sorted.txt";
		List<OrderDetailsDto> list = detailTransformService.readFileFromSource(sourceLocation,destinationLocation);
		assertThat(list).isEmpty();
	}
	
	@Test
	public void testReadFileFromSource_whenSourceFileIsCorrect() throws Exception  {
		OrderDetailTransformService detailTransformService = new OrderDetailTransformService();
		String sourceLocation = "C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_ordered.txt";
		String destinationLocation="C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_sorted.txt";
		List<OrderDetailsDto> list = detailTransformService.readFileFromSource(sourceLocation,destinationLocation);
		assertThat(list).isNotEmpty();
	}
	
	@Test
	public void testReadFileFromSource_whenDestinationLocationIsNotCorrect() throws Exception  {
		OrderDetailTransformService detailTransformService = new OrderDetailTransformService();
		String sourceLocation = "C://src//test//resources//sample_data_ordered.txt";
		String destinationLocation="C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_sorted.txt";
		List<OrderDetailsDto> list = detailTransformService.readFileFromSource(sourceLocation,destinationLocation);
		assertThat(list).isEmpty();
	}
	
	@Test
	public void testReadFileFromSource_whenDestinationLocationIsCorrect() throws Exception  {
		OrderDetailTransformService detailTransformService = new OrderDetailTransformService();
		String sourceLocation = "C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_ordered.txt";
		String destinationLocation="C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_sorted.txt";
		List<OrderDetailsDto> list = detailTransformService.readFileFromSource(sourceLocation,destinationLocation);
		assertThat(list).isNotEmpty();
	}
	
	
	@Test
	public void testwriteFileToDestination_whenListIsNull() throws Exception  {
		OrderDetailTransformService detailTransformService = new OrderDetailTransformService();
		List<OrderDetailsDto> orderDetailList = null;
		String destinationLocation="C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_sorted.txt";
		Boolean isWrite = detailTransformService.writeFileToDestination(orderDetailList, destinationLocation);
		assertThat(isWrite).isEqualTo(false);
		}
	
	@Test
	public void testwriteFileToDestination_whenListIsEmpty() throws Exception  {
		OrderDetailTransformService detailTransformService = new OrderDetailTransformService();
		List<OrderDetailsDto> orderDetailList = new ArrayList<>();
		String destinationLocation="C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_sorted.txt";
		Boolean isWrite = detailTransformService.writeFileToDestination(orderDetailList, destinationLocation);
		assertThat(isWrite).isEqualTo(false);
		}
	@Test
	public void testwriteFileToDestination_whenDestinationLocationIsNotCorrect() throws Exception  {
		OrderDetailTransformService detailTransformService = new OrderDetailTransformService();
		List<OrderDetailsDto> orderDetailList = new ArrayList<>();
		String destinationLocation="C://PizzaOrder_Aquent//src//test//resources//sample_data_sorted.txt";
		Boolean isWrite = detailTransformService.writeFileToDestination(orderDetailList, destinationLocation);
		assertThat(isWrite).isEqualTo(false);
		}
	
	@Test
	public void testReadFileFromSource_whenTimeIsNotCorrect() throws Exception  {
		OrderDetailTransformService detailTransformService = new OrderDetailTransformService();
		String sourceLocation = "C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_ordered2.txt";
		String destinationLocation="C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_sorted2.txt";
		List<OrderDetailsDto> list = detailTransformService.readFileFromSource(sourceLocation,destinationLocation);
		assertThat(list.get(0).getOrderTime()).isEqualTo(0L);
	}
	
}