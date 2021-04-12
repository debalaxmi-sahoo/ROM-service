package com.smarthost.rom.domain;



public class BookingDetails {
	
	public static enum Status {Confirmed,Available};
	
	public static enum RoomType {Premium,Economic};

	private Status bookingStatus;
	
	private RoomType roomType;
	
	public BookingDetails (Double price,RoomType roomType,Status bookingStatus) {
		this.roomType = roomType;
		this.bookingStatus = bookingStatus;
		this.price = price;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	private Double price = null;

	public Status getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(Status bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
		
}
