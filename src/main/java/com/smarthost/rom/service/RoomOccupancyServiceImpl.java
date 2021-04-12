package com.smarthost.rom.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.smarthost.rom.domain.AvailableRoom;
import com.smarthost.rom.domain.Booking;
import com.smarthost.rom.domain.BookingDetails;

@Component
public class RoomOccupancyServiceImpl implements RoomOccupancyService {

	private static final Logger log = LoggerFactory.getLogger(RoomOccupancyServiceImpl.class);
	
	public Booking processBookings(AvailableRoom availableRoom,List<Double> customerPayments) throws Exception{
		
		Booking booking = new Booking();
		if(availableRoom == null || CollectionUtils.isEmpty(customerPayments)) {
			log.error("Exception while executing processBookings due to invalid inputs");
			throw new Exception("Bad request - Invalid Inputs");
		}
       
		int availablePremiumCount = availableRoom.getAvailablePremiumCount();
		int availableEconomicCount = availableRoom.getAvailableEconomicCount();
		
		List<Double> economyPriceOffers = customerPayments.stream().filter(i -> i < 100).collect(Collectors.toList());
        List<Double> premiumPriceOffers = customerPayments.stream().filter(i -> i >= 100).collect(Collectors.toList());
						
    	Collections.sort(economyPriceOffers,Collections.reverseOrder());
    	Collections.sort(premiumPriceOffers,Collections.reverseOrder());
    	
    	int upgradeToPremiumCount = availablePremiumCount - premiumPriceOffers.size();
    	
    	if(premiumPriceOffers.size() > availablePremiumCount) {
    		premiumPriceOffers = premiumPriceOffers.subList(0, availablePremiumCount);
    	}
    	
        List<BookingDetails> bookingDetais = new ArrayList<>();
        List<BookingDetails> premiumBookings = new ArrayList<>();
        List<BookingDetails> economicBookings = new ArrayList<>();
    	
    	premiumPriceOffers.forEach(payment ->{
			if(availableRoom.getAvailablePremiumCount() > 0) {
				premiumBookings.add(new BookingDetails(payment,BookingDetails.RoomType.Premium,BookingDetails.Status.Confirmed));
			}
		});
    	
    	
    	for(Double payment : economyPriceOffers) {
			if(upgradeToPremiumCount > 0 && economyPriceOffers.size() > availableRoom.getAvailableEconomicCount()) {				
				premiumBookings.add(new BookingDetails(payment,BookingDetails.RoomType.Premium,BookingDetails.Status.Confirmed));
				upgradeToPremiumCount --;
				availablePremiumCount--;
			}else if(availableEconomicCount >0){				
				economicBookings.add(new BookingDetails(payment,BookingDetails.RoomType.Economic,BookingDetails.Status.Confirmed));
				availableEconomicCount--;
			}
		}
		
		booking.setEconomicCount(economicBookings.size());
		booking.setPremiumCount(premiumBookings.size());
		booking.setEconomicTotal(economicBookings.stream().mapToDouble(BookingDetails::getPrice).sum());
		booking.setPremiumTotal(premiumBookings.stream().mapToDouble(BookingDetails::getPrice).sum());
		
		log.info("booked Premium Room Count -- {}",booking.getPremiumCount()); 
		log.info("booked Economic Room Count -- {}",booking.getEconomicCount());
		log.info("booked Premium Room total price -- {}",booking.getPremiumTotal()); 
		log.info("booked Economic Room total price -- {}",booking.getEconomicTotal());
		
		bookingDetais.addAll(economicBookings);
		bookingDetais.addAll(premiumBookings);		
		booking.setBookingDetails(bookingDetais);
		
		return booking;
	}
}
