package com.ca.msm.weipe03.homework.service;

import java.util.List;

import com.ca.msm.weipe03.homework.entity.Bus;

public class SchoolTripResult {

	private List<Bus> usedBuses;
	private boolean isEnoughSeats;

	public SchoolTripResult(List<Bus> usedBuses, boolean isEnoughSeats) {
		this.usedBuses = usedBuses;
		this.isEnoughSeats = isEnoughSeats;
	}
	
	public List<Bus> getUsedBuses(){
		return this.usedBuses;
	}
	
	public boolean hasEnoughCapacity(){
		return this.isEnoughSeats;
	}
	
	
}
