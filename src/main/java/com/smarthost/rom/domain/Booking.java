package com.smarthost.rom.domain;

import java.util.ArrayList;
import java.util.List;

public class Booking {
	
	private List<BookingDetails> bookingDetails = new ArrayList<>();
		
	private double premiumTotal;
	private double economicTotal;	
	private int economicCount = 0;	
	private int premiumCount= 0;
		
	public double getPremiumTotal() {
		return premiumTotal;
	}

	public void setPremiumTotal(double premiumTotal) {
		this.premiumTotal = premiumTotal;
	}

	public double getEconomicTotal() {
		return economicTotal;
	}

	public void setEconomicTotal(double economicTotal) {
		this.economicTotal = economicTotal;
	}

	public int getEconomicCount() {
		return economicCount;
	}

	public void setEconomicCount(int economicCount) {
		this.economicCount = economicCount;
	}

	public int getPremiumCount() {
		return premiumCount;
	}

	public void setPremiumCount(int premiumCount) {
		this.premiumCount = premiumCount;
	}

	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
		
}
