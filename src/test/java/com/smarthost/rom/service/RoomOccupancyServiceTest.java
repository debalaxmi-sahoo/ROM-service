package com.smarthost.rom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.smarthost.rom.domain.AvailableRoom;
import com.smarthost.rom.domain.Booking;
import com.smarthost.rom.util.JsonUtil;

/**
 * @author Debalaxmi.Sahoo
 *
 */

@SpringBootTest
public class RoomOccupancyServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(RoomOccupancyServiceTest.class);
		
	@InjectMocks
	RoomOccupancyServiceImpl underTest;
	
	
	@BeforeEach
	public void setup() {
		//MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testProcessBookings() throws Exception {
		log.info("Executing testProcessBookings ");
		Booking response = new Booking();
		
		List<Double> payments = JsonUtil.getCustomerPaymentDetails();
		//Test 1 
		response = underTest.processBookings(new AvailableRoom(3,3),payments);
			
		assertEquals(3, response.getPremiumCount());
		assertEquals(3, response.getEconomicCount());
		assertEquals(Double.valueOf("167.99"),response.getEconomicTotal());
		assertEquals(Double.valueOf("738"),response.getPremiumTotal());
		
		//Test 2 
		response = underTest.processBookings(new AvailableRoom(7,5),payments);
		
		assertEquals(6, response.getPremiumCount());
		assertEquals(4, response.getEconomicCount());
		assertEquals(Double.valueOf("189.99"),response.getEconomicTotal());
		assertEquals(Double.valueOf("1054"),response.getPremiumTotal());
		
		//Test 3
		response = underTest.processBookings(new AvailableRoom(2,7),payments);
		
		assertEquals(2, response.getPremiumCount());
		assertEquals(4, response.getEconomicCount());
		assertEquals(Double.valueOf("189.99"),response.getEconomicTotal());
		assertEquals(Double.valueOf("583"),response.getPremiumTotal());
		
		
		//Test 4
		response = underTest.processBookings(new AvailableRoom(7,1),payments);
		
		assertEquals(7, response.getPremiumCount());
		assertEquals(1, response.getEconomicCount());
		assertEquals(Double.valueOf("45.0"),response.getEconomicTotal());
		assertEquals(Double.valueOf("1153.99"),response.getPremiumTotal());
		
	}
	
}
