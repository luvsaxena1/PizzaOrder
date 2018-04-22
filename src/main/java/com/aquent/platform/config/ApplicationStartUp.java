package com.aquent.platform.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.aquent.platform.service.IOrderDetailTransformService;

/**
 * @author Luv Saxena This class is used to call orderDetailTransformService
 *         once the application is up and running It implements
 *         ApplicationListner class in order to override onApplicationEvent
 *         method which get executed when the application is up and running.
 */
@Component
public class ApplicationStartUp implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);
	@Autowired
	IOrderDetailTransformService orderDetailTransformService;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		logger.info("ApplicationStartUp.onApplicationEvent():: with parameter event {}",event);
		orderDetailTransformService.readFileFromSource();
	}
}
