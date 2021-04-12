package com.smarthost.rom.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthost.rom.domain.AvailableRoom;
import com.smarthost.rom.domain.Booking;
import com.smarthost.rom.service.RoomOccupancyService;
import com.smarthost.rom.util.JsonUtil;
import com.smarthost.rom.web.ServiceResult;

/*
 * @author Debalaxmi.Sahoo
 */
@RestController
@RequestMapping("/api")
public class RoomOccupancyController {
	
    private static final Logger log = LoggerFactory.getLogger(RoomOccupancyController.class);
    
	@Autowired
	private RoomOccupancyService roomOccupancyService = null;
	
	@RequestMapping(path = "/processBookings",method = RequestMethod.GET)
	public ServiceResult<Booking> processBookings(@RequestParam String availablePremRoomCount,@RequestParam String availableEconoRoomCount) {
			
		Booking booking = null;
		try {
			log.info("available Premium Room Count -- {}",availablePremRoomCount); 
			log.info("available Economic Room Count -- {}",availableEconoRoomCount);
			List<Double> customerPayments = JsonUtil.getCustomerPaymentDetails();
			booking = roomOccupancyService.processBookings(new AvailableRoom(Integer.parseInt(availablePremRoomCount),Integer.parseInt(availableEconoRoomCount)), customerPayments);

		}catch(Exception ex){
			log.error("Exception while processBookings--",ex.getMessage());
			ServiceResult<Booking> serviceResult =  new ServiceResult<Booking>(new Booking(),new ArrayList<>());
			return serviceResult;
		}	
		return new ServiceResult<Booking>(booking);

	}
	
}
