package com.ca.msm.weipe03.homework.data;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

public class DataProviderFactoryTest{
	public static IDataProvider provider;
	@Test
	public void get_data_provider() throws IOException {
		provider = DataProviderFactory.getDataProvider(new String[]{"-code"});		
		assertNotNull(provider);
		assertTrue(provider instanceof DataHardcodedProvider);
		assertEquals(DataHardcodedProvider.class, provider.getClass());
		
		provider = DataProviderFactory.getDataProvider(new String[]{"-keyboard"});
		assertNotNull(provider);
		assertTrue(provider instanceof DataFromKeyboardProvider);
		assertEquals(DataHardcodedProvider.class, provider.getClass());

		provider = DataProviderFactory.getDataProvider(new String[]{"-file", "c:\\somefile.txt"});
		assertNotNull(provider);
		assertTrue(provider instanceof DataFromFileProvider);

		provider = DataProviderFactory.getDataProvider(new String[]{"-blablabla"});
		assertNull(provider);

		provider = DataProviderFactory.getDataProvider(null);
		assertNull(provider);

	}

}
