package com.ca.msm.weipe03.homework.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public class DataFromFileProviderTest {
	public static DataFromFileProvider provider;

	@BeforeClass
	public static void setup() throws DataReadException{
		provider = new DataFromFileProvider("testdata.txt");
	}
	@AfterClass
	public static void finish(){
		provider = null;
	}
	
	@Test
	public void constructor_DataFromFileProvider() throws DataReadException{
		provider = new DataFromFileProvider("testdata.txt");
		
		assertNotNull(provider);
	}
	
	@Test
	public void provide_buses() throws DataReadException{
		List<Bus> expectedBuses = new ArrayList<Bus>();
		expectedBuses.add(new Bus.Builder(11).withName("test_bus_a").build());
		expectedBuses.add(new Bus.Builder(22).withName("test_bus_b").build());
		expectedBuses.add(new Bus.Builder(33).withName("test_bus_c").build());
		expectedBuses.add(new Bus.Builder(44).withName("test_bus_d").build());
		
		List<Bus> result = provider.provideBuses();
		
		assertArrayEquals(expectedBuses.toArray(), result.toArray());
		assertNotSame(expectedBuses, result);
	}
	
	@Test
	public void provide_school_class() throws DataReadException{
		SchoolClass expectedClass = new SchoolClass.Builder(20).withName("test_class_name").build();
		
		SchoolClass result = provider.provideSchoolClass();
		
		assertEquals(expectedClass, result);
		assertNotSame(expectedClass, result);
	}
}
