package com.aquent.platform.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aquent.platform.dto.OrderDetailsDto;
import com.aquent.platform.service.IOrderDetailTransformService;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderDetailsController.class)
public class OrderDetailsControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IOrderDetailTransformService detailTransformService;

	@Test
	public void testConvertFile_WhenSourceAndDestination_IsNotCorrect() throws Exception {

		String sourceLocation = "C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_ordered1.txt";
		String destinationLocation = "C://Users//windows//Documents//Workspace1//PizzaOrder_Aquent//src//test//resources//sample_data_sorted.txt";

		OrderDetailsDto detailsDto1 = new OrderDetailsDto();
		detailsDto1.setOrderName("Pizza");
		detailsDto1.setOrderTime(12131212L);
		OrderDetailsDto detailsDto2 = new OrderDetailsDto();
		detailsDto1.setOrderName("Meat");
		detailsDto1.setOrderTime(1252L);

		List<OrderDetailsDto> list = new ArrayList<>();
		list.add(detailsDto1);
		list.add(detailsDto2);

		Mockito.when(detailTransformService.readFileFromSource(sourceLocation, destinationLocation)).thenReturn(list);

		mvc.perform(get("/v1/order/convert").contentType(MediaType.APPLICATION_JSON_VALUE))
				// .andExpect(jsonPath("$",
				// hasSize(2))).andExpect(jsonPath("$[0].orderName",
				// is("MEAT")))
				// .andExpect(jsonPath("$[0].orderTime",
				// is(1252))).andExpect(jsonPath("$[1].orderName", is("PIZZA")))
				// .andExpect(jsonPath("$[1].orderTime", is(12131212)));
				.andExpect(jsonPath("$", hasSize(0)));
	}
}