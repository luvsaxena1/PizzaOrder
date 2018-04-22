package com.aquent.platform.service.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aquent.platform.Application;
import com.aquent.platform.dto.OrderDetailsDto;
import com.aquent.platform.service.IOrderDetailTransformService;
import com.aquent.platform.util.OrderDetailsSortingUtil;

/**
 * @author Luv Saxena TheOrderDetailTransformService is Responsible for reading
 *         the Order Details from the file present in the source location
 *         provided by the user and write the data of the file in the human
 *         readable format to the new file should be created to the destination
 *         folder provided by the user.
 */
@Service
public class OrderDetailTransformService implements IOrderDetailTransformService {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Value("${file.source}")
	private String sourceLocation;

	@Value("${file.destination}")
	private String destinationLocation;

	private List<OrderDetailsDto> orderDetailList = new ArrayList<>();

	public Boolean readFileFromSource() {
		logger.info(
				"OrderDetailTransformService.readFileFromSource():: Request is recieved from ApplicationStartUp to execute readFileFromSource()");
		try (Stream<String> stream = Files.lines(Paths.get(sourceLocation))) {
			stream.forEach(this::process);
		} catch (NoSuchFileException e) {
			logger.error("Error caught because file name not found at location {}", sourceLocation);
			e.printStackTrace();
		}

		catch (IOException e) {
			logger.error("Error caught while reading or opening the file on location {}", sourceLocation);
			e.printStackTrace();
		}
		Collections.sort(orderDetailList, new OrderDetailsSortingUtil());
		return writeFileToDestination(orderDetailList);
	}

	@Override
	public Boolean writeFileToDestination(List<OrderDetailsDto> orderDetailList) {
		logger.info(
				"OrderDetailTransformService.writeFileToDestination():: Request is recieved to write to destination file on location {}",
				destinationLocation);

		Path path = Paths.get(destinationLocation);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write("Order" + "\t\t\t" + "Time IN GMT" + "\t\t\t" + "Time In Your Zone");
			writer.newLine();
			if (!(orderDetailList.isEmpty() || orderDetailList == null)) {
				for (OrderDetailsDto detailsDto : orderDetailList) {

					LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(detailsDto.getOrderTime() * 1000),
							ZoneId.systemDefault());
					LocalDateTime date1 = LocalDateTime
							.ofInstant(Instant.ofEpochMilli(detailsDto.getOrderTime() * 1000), ZoneId.of("UTC"));
					writer.write(detailsDto.getOrderName() + "\t\t\t"
							+ date1.format(DateTimeFormatter.ofPattern("d:MMM:uuuu HH:mm:ss")) + "\t\t\t"
							+ date.format(DateTimeFormatter.ofPattern("d:MMM:uuuu HH:mm:ss")));
					writer.newLine();
				}
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error caught while writing or opening the destination file on location {}",
					destinationLocation);
			e.printStackTrace();
		}
		return false;
	}

	private void process(String s) {
		logger.info(
				"OrderDetailTransformService.process():: Request is recieved to conert each line to OrderDetailsDto object");
		if (StringUtils.isEmpty(s))
			return;
		String[] eachLine = s.split("\\t+");
		if (!(eachLine[0].equalsIgnoreCase("Order") || eachLine[1].equalsIgnoreCase("time"))) {
			OrderDetailsDto order = new OrderDetailsDto();
			order.setOrderName(eachLine[0].toUpperCase());
			try {
				order.setOrderTime(Long.parseLong(eachLine[1]));
			} catch (NumberFormatException exception) {
				logger.error("Error caught while parsing the order time {} from string to long", eachLine[1]);
				exception.printStackTrace();
			}
			orderDetailList.add(order);
		}
	}
}
