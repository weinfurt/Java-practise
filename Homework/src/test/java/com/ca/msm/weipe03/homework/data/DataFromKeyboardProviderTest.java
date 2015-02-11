package com.ca.msm.weipe03.homework.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class DataFromKeyboardProviderTest {

	
	
	@Test
	public void testDataFromKeyboardProvider() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testProvideBuses() {
		fail("Not yet implemented");
	}

	@Test
	public void testProvideSchoolClass() {
		DataFromKeyboardProvider kp = mock(DataFromKeyboardProvider.class);
		when(kp.readStringFromKeyboard("prompt")).thenReturn("name");
		fail("Not yet implemented");
	}

}
