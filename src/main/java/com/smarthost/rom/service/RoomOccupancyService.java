package com.smarthost.rom.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.smarthost.rom.domain.AvailableRoom;
import com.smarthost.rom.domain.Booking;

@Component
public interface RoomOccupancyService {

	Booking processBookings(AvailableRoom request,List<Double> customerPayment) throws Exception ;
	
}
