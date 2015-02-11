package com.ca.msm.weipe03.homework.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public class DataHardcodedProviderTest {
	public static DataHardcodedProvider provider;
	
	@BeforeClass
	public static void setup(){
		provider = new DataHardcodedProvider();
	}
	@AfterClass
	public static void finish(){
		provider = null;
	}
	
	@Test
	public void constructor_DataHardcodedProvider(){
		provider = new DataHardcodedProvider();
		
		assertNotNull(provider);
	}

	@Test
	public void provide_buses() throws CloneNotSupportedException{
		List<Bus> expectedBuses = new ArrayList<Bus>();
		expectedBuses.add(new Bus.Builder(49).withName("Renault").build());
		expectedBuses.add(new Bus.Builder(66).withName("Scania").build());
		expectedBuses.add(new Bus.Builder(48).withName("Man").build());
		expectedBuses.add(new Bus.Builder(10).withName("Transporter").build());
		expectedBuses.add(new Bus.Builder(14).withName("MiniBus").build());
		expectedBuses.add(new Bus.Builder(75).withName("Karosa").build());
		
		List<Bus> result = provider.provideBuses();
		
		assertArrayEquals(expectedBuses.toArray(), result.toArray());
		assertNotSame(expectedBuses, result);
	}
	
	@Test
	public void provide_school_class() throws CloneNotSupportedException{
		SchoolClass expectedClass = new SchoolClass.Builder(74).withName("Kvarta B").build();
		
		SchoolClass result = provider.provideSchoolClass();
		
		assertEquals(expectedClass, result);
		assertNotSame(expectedClass, result);
	}
}
