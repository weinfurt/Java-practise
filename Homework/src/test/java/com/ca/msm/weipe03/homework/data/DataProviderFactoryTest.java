package com.ca.msm.weipe03.homework.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataProviderFactoryTest{
	public static IDataProvider provider;
	@Test
	public void get_data_provider() throws DataReadException {
		provider = DataProviderFactory.getDataProvider(new String[]{"-CODE"});		
		assertNotNull(provider);
		assertEquals(DataHardcodedProvider.class, provider.getClass());
		
		provider = DataProviderFactory.getDataProvider(new String[]{"-KEYBOARD"});
		assertNotNull(provider);
		assertEquals(DataFromKeyboardProvider.class, provider.getClass());

		provider = DataProviderFactory.getDataProvider(new String[]{"-FILE", "testdata.txt"});
		assertNotNull(provider);
		assertEquals(DataFromFileProvider.class, provider.getClass());

		provider = DataProviderFactory.getDataProvider(new String[]{"-FILE", "testdata.xml"});
		assertNotNull(provider);
		assertEquals(DataFromJaxbProvider.class, provider.getClass());

		provider = DataProviderFactory.getDataProvider(new String[]{"-FILE"});
		assertNull(provider);

		provider = DataProviderFactory.getDataProvider(new String[]{"-INVALID_ARG"});
		assertNull(provider);

		provider = DataProviderFactory.getDataProvider(null);
		assertNull(provider);
	}
	
	@Test(expected=DataReadException.class)
	public void get_invalid_data_file_provider() throws DataReadException{
		provider = DataProviderFactory.getDataProvider(new String[]{"-FILE", "nonexistent.txt"});
		assertNotNull(provider);
		assertEquals(DataFromFileProvider.class, provider.getClass());	
	}
	
	@Test(expected=DataReadException.class)
	public void get_invalid_xml_file_provider() throws DataReadException{
		provider = DataProviderFactory.getDataProvider(new String[]{"-FILE", "nonexistent.xml"});
		assertNotNull(provider);
		assertEquals(DataFromJaxbProvider.class, provider.getClass());		
	}

}
