package com.ca.msm.weipe03.homework.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public class SchoolTripTest {
	public static SchoolTrip trip;
	public static List<Bus> buses;
	public static List<Bus> busesSorted;
	
	@BeforeClass
	public static void setup() throws CloneNotSupportedException{
		trip = new SchoolTrip();
		buses = new ArrayList<Bus>();
		buses.add(new Bus.Builder(24).withName("test_bus_a").build());
		buses.add(new Bus.Builder(18).withName("test_bus_b").build());
		buses.add(new Bus.Builder(47).withName("test_bus_c").build());
		buses.add(new Bus.Builder(36).withName("test_bus_d").build());
		buses.add(new Bus.Builder(55).withName("test_bus_e").build());
		busesSorted = new ArrayList<Bus>();
		busesSorted.add(new Bus.Builder(55).withName("test_bus_e").build());
		busesSorted.add(new Bus.Builder(47).withName("test_bus_c").build());
		busesSorted.add(new Bus.Builder(36).withName("test_bus_d").build());
		busesSorted.add(new Bus.Builder(24).withName("test_bus_a").build());
		busesSorted.add(new Bus.Builder(18).withName("test_bus_b").build());

	}
	@AfterClass
	public static void finish(){
		trip = null;
		buses.clear();
		buses = null;
		busesSorted.clear();
		busesSorted = null;
	}

	@Test
	public void constructor_SchoolTrip(){
		trip = new SchoolTrip();
		
		assertNotNull(trip);
	}
	
	@Test
	public void is_enough_space() throws CloneNotSupportedException{
		List<Bus> originalBuses = new ArrayList<Bus>(buses);
		
		boolean result = trip.isEnoughSpace(48, buses);
		assertTrue(result);
		assertArrayEquals("Original list should not have been modified.", originalBuses.toArray(), buses.toArray());

		result = trip.isEnoughSpace(181, buses);
		assertFalse(result);
		assertArrayEquals("Original list should not have been modified.", originalBuses.toArray(), buses.toArray());
	}
	@Test
	public void sort_buses() throws CloneNotSupportedException{
		List<Bus> originalBuses = new ArrayList<Bus>(buses);
		
		List<Bus> result = trip.sortBuses(buses);
		assertArrayEquals("Result list should be sorted in descending order.", busesSorted.toArray(), result.toArray());
		assertArrayEquals("Original list should not have been modified.", originalBuses.toArray(), buses.toArray());
	}
	@Test
	public void buses_used() throws CloneNotSupportedException{		
		List<Bus> originalBuses = new ArrayList<Bus>(busesSorted);

		List<Bus> expectedBuses = new ArrayList<Bus>();
		expectedBuses.add(new Bus(busesSorted.get(0)));
		expectedBuses.add(new Bus(busesSorted.get(1)));
		expectedBuses.add(new Bus(busesSorted.get(2)));

		List<Bus> result = trip.busesUsed(130, busesSorted);
		assertArrayEquals(expectedBuses.toArray(), result.toArray());
		assertArrayEquals("Original list should not have been modified.", originalBuses.toArray(), busesSorted.toArray());
		
		expectedBuses.clear();
		result = trip.busesUsed(0, busesSorted);
		assertArrayEquals(expectedBuses.toArray(), result.toArray());
		assertArrayEquals("Original list should not have been modified.", originalBuses.toArray(), busesSorted.toArray());		
	}
	@Test
	public void trip_result() throws CloneNotSupportedException {
		List<Bus> originalBuses = new ArrayList<Bus>(buses);
		
		List<Bus> expectedBuses = new ArrayList<Bus>();
		expectedBuses.add(new Bus(busesSorted.get(0)));
		expectedBuses.add(new Bus(busesSorted.get(1)));
		expectedBuses.add(new Bus(busesSorted.get(2)));
		
		SchoolTripResult result = trip.tripResult(new SchoolClass.Builder(110).withName("test_class_a").build(), buses);
		
		assertArrayEquals(expectedBuses.toArray(), result.getUsedBuses().toArray());
		assertTrue(result.hasEnoughCapacity());
		
		assertArrayEquals("Original list should not have been modified.", originalBuses.toArray(), buses.toArray());

		expectedBuses = new ArrayList<Bus>();
		expectedBuses.add(new Bus(busesSorted.get(0)));
		expectedBuses.add(new Bus(busesSorted.get(1)));
		expectedBuses.add(new Bus(busesSorted.get(2)));
		expectedBuses.add(new Bus(busesSorted.get(3)));
		expectedBuses.add(new Bus(busesSorted.get(4)));

		result = trip.tripResult(new SchoolClass.Builder(181).withName("test_class_b").build(), buses);
		assertArrayEquals(expectedBuses.toArray(), result.getUsedBuses().toArray());
		assertFalse(result.hasEnoughCapacity());

		assertArrayEquals("Original list should not have been modified.", originalBuses.toArray(), buses.toArray());
	}
}