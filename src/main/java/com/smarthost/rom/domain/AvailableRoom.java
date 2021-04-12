package com.smarthost.rom.domain;



public class AvailableRoom {
	
	
	public AvailableRoom(int availablePremiumCount, int availableEconomicCount) {		
		this.availablePremiumCount = availablePremiumCount;
		this.availableEconomicCount = availableEconomicCount;
	}

	private int availablePremiumCount = 0;
	
	private int availableEconomicCount = 0;

	public int getAvailablePremiumCount() {
		return availablePremiumCount;
	}

	public void setAvailablePremiumCount(int availablePremiumCount) {
		this.availablePremiumCount = availablePremiumCount;
	}

	public int getAvailableEconomicCount() {
		return availableEconomicCount;
	}

	public void setAvailableEconomicCount(int availableEconomicCount) {
		this.availableEconomicCount = availableEconomicCount;
	}

	
}