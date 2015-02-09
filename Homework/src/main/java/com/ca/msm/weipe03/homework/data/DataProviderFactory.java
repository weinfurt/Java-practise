package com.ca.msm.weipe03.homework.data;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataProviderFactory {
	private static final Logger logger = LoggerFactory.getLogger(DataProviderFactory.class);
	
	public DataProviderFactory() {
		// TODO Auto-generated constructor stub
	}

	public static IDataProvider getDataProvider(String arguments[]) throws IOException {
		logger.debug("getDataProvider()");
		if (arguments == null){
			logger.warn("No parameters has been passed in.");
			return null;
		}
		logger.trace("input: arguments={}", Arrays.toString(arguments));
		if (arguments.length > 0) {
			switch (arguments[0]) {
			case "-keyboard":
				return new DataFromKeyboardProvider();
			case "-code":
				return new DataHardcodedProvider();
			case "-file":
				if (arguments.length > 1)
					return new DataFromFileProvider(arguments[1]);
				else
					return null;
			case "-jaxb":
				if (arguments.length > 1)
					return new DataFromJaxbProvider(arguments[1]);
				else
					return null;
			default:
				return null;
			}
		}
		return null;
	}

}