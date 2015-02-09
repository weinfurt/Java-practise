package com.ca.msm.weipe03.homework.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;


/**
 * @author weipe03
 *
 */
public class SchoolTrip implements ISchoolTrip{
	
	private static final Logger logger = LoggerFactory.getLogger(ISchoolTrip.class);
	
	public SchoolTrip() {		
	}
	
	public boolean isEnoughSpace(int passengers, List<Bus> buses){
		int totalCapacity = 0;		
		logger.debug("isEnoughSpace()");
		logger.trace("input: passengers={}", passengers);
		logger.trace("input: buses={}", buses);
		Iterator<Bus> it = buses.iterator();
		while(it.hasNext()){
			totalCapacity += it.next().getCapacity();
			logger.trace("new total capacity={}", totalCapacity);
		}
		if (passengers > totalCapacity){
			logger.trace("output: false");
			return false;
		} else{
			logger.trace("output: true");
			return true;
		}
	}
	protected List<Bus> sortBuses(List<Bus> buses){
		logger.debug("sortBuses()");
		logger.trace("input: buses={}", buses);
		List<Bus> sortedBuses = new ArrayList<Bus>(buses);
		Collections.sort(sortedBuses, BusComparator.getInstance());
		logger.trace("output: sortedBuses={}", sortedBuses);
		return sortedBuses;
	}
	
	public List<Bus> busesUsed(int seatsNeeded, List<Bus> orderedBuses){
		logger.debug("busesUsed()");
		logger.trace("input: seatsNeeded={}", seatsNeeded);
		logger.trace("input: orderedBuses={}", orderedBuses);
		List<Bus> usedBuses = new ArrayList<Bus>();
		if (seatsNeeded <= 0){
			logger.warn("The input parameter \"seatsNeeded\" should not be 0 or less than 0.");
			return usedBuses;
		}
		Iterator<Bus> it = orderedBuses.iterator();
		while(it.hasNext()){
			Bus bus = it.next();
			logger.trace("adding new bus to list: {}", bus.toString());
			usedBuses.add(bus);
			seatsNeeded -= bus.getCapacity();
			if (seatsNeeded < 0)
				break;
		}
		logger.trace("output: usedBuses={}", usedBuses);
		return usedBuses;
	}

	@Override
	public SchoolTripResult tripResult(SchoolClass schoolClass, List<Bus> buses){
		logger.debug("tripResult()");
		logger.trace("input: schoolClass={}", schoolClass);
		logger.trace("input: buses={}", buses);
		return new SchoolTripResult(busesUsed(schoolClass.getStudentsCount(), sortBuses(buses)), isEnoughSpace(schoolClass.getStudentsCount(), buses));
	}
}
