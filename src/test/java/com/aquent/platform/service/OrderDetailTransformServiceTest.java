package com.aquent.platform.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aquent.platform.dto.OrderDetailsDto;
import com.aquent.platform.service.impl.OrderDetailTransformService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailTransformServiceTest {

	@Autowired
	private OrderDetailTransformService detailTransformService;

	@Test
	public void testReadFileFromSource_whenSourceLocationIsNotCorrect() throws NoSuchFileException {
		detailTransformService.readFileFromSource();
	}

	@Test
	public void testReadFileFromSource_whenSourceLocationIsCorrect() {
		List<OrderDetailsDto> orderDetailList = new ArrayList<>();
		OrderDetailsDto orderDetaildto1 = new OrderDetailsDto();
		orderDetaildto1.setOrderName("Pizza");
		orderDetaildto1.setOrderTime(1433324343L);
		OrderDetailsDto orderDetaildto2 = new OrderDetailsDto();
		orderDetaildto2.setOrderName("Meat");
		orderDetaildto2.setOrderTime(1433654443L);
		orderDetailList.add(orderDetaildto1);
		orderDetailList.add(orderDetaildto2);
		assertThat(detailTransformService.readFileFromSource()).isEqualTo(true);
	}

	@Test
	public void process_whenStringIsOrderAndTime() {
		String s = "Order		Time";
		assertThat(detailTransformService.readFileFromSource()).isEqualTo(true);
	}
}