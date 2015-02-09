package com.ca.msm.weipe03.homework.service;

import java.util.Comparator;

import com.ca.msm.weipe03.homework.entity.Bus;

public class BusComparator implements Comparator<Bus> {
	
	private static final BusComparator INSTANCE = new BusComparator();
	
	private BusComparator(){		
	}
	
	public static BusComparator getInstance(){
		return INSTANCE;
	}

	@Override
	public int compare(Bus o1, Bus o2) {
		return o2.getCapacity() - o1.getCapacity();
	}
}