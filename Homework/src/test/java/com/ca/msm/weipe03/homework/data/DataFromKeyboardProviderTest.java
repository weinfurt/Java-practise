package com.ca.msm.weipe03.homework.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public class DataFromKeyboardProviderTest {
	public static DataFromKeyboardProvider provider;

	@BeforeClass
	public static void setup() throws DataReadException{
		provider = new DataFromKeyboardProvider();
	}
	@AfterClass
	public static void finish(){
		provider = null;
	}
	
	@Test
	public void constructor_DataFromKeyboardProvider(){
		provider = new DataFromKeyboardProvider();
		assertNotNull(provider);
	}

	@Test
	public void provide_buses() throws DataReadException, IOException{
		BufferedReader br = mock(BufferedReader.class);
		when(br.readLine()).thenReturn("3", "test bus a", "74", "test bus b", "47", "test bus c", "25");
		
		List<Bus> expectedBuses = new ArrayList<Bus>();
		expectedBuses.add(new Bus.Builder(74).withName("test bus a").build());
		expectedBuses.add(new Bus.Builder(47).withName("test bus b").build());
		expectedBuses.add(new Bus.Builder(25).withName("test bus c").build());
		
		List<Bus> result = provider.provideBuses(br);

		assertArrayEquals(expectedBuses.toArray(), result.toArray());
		assertNotSame(expectedBuses, result);
	}

	@Test
	public void provide_school_class() throws DataReadException, IOException{
		BufferedReader br = mock(BufferedReader.class);
		when(br.readLine()).thenReturn("test class name").thenReturn("45");

		SchoolClass expectedClass = new SchoolClass.Builder(45).withName("test class name").build();

		SchoolClass result = provider.provideSchoolClass(br);
		assertEquals(expectedClass, result);
		assertNotSame(expectedClass, result);
	}

}
