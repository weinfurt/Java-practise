package com.ca.msm.weipe03.homework.service;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.hamcrest.core.IsSame;
import org.hamcrest.number.OrderingComparison;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ca.msm.weipe03.homework.entity.Bus;

public class BusComparatorTest {
	
	public static Bus BUS_A;
	public static Bus BUS_B;

	@BeforeClass
	public static void setup() throws CloneNotSupportedException{
		BUS_A = new Bus.Builder(24).withName("test_bus_a").build();
		BUS_B = new Bus.Builder(44).withName("test_bus_b").build();
	}
	@AfterClass
	public static void finish(){
		BUS_A = null;
		BUS_B = null;
	}
	
	@Test
	public void compare(){
		Comparator<Bus> comparator = BusComparator.getInstance();
		assertThat(comparator.compare(BUS_A, BUS_A), OrderingComparison.comparesEqualTo(0));
		assertThat(comparator.compare(BUS_A, BUS_B), OrderingComparison.greaterThan(0));
		assertThat(comparator.compare(BUS_B, BUS_A), OrderingComparison.lessThan(0));		
	}
	
	@Test
	public void get_instance(){
		assertNotNull(BusComparator.getInstance());
		assertThat(BusComparator.getInstance(), IsSame.sameInstance(BusComparator.getInstance()));
	}

}
