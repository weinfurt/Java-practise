package com.ca.msm.weipe03.homework.data;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataProviderFactory {
	private static final Logger logger = LoggerFactory.getLogger(DataProviderFactory.class);
	
	public DataProviderFactory() {
		// TODO Auto-generated constructor stub
	}

	public static IDataProvider getDataProvider(String arguments[]) throws DataReadException{
		logger.debug("getDataProvider()");
		if (arguments == null){
			logger.error("No parameters has been passed in.");
			return null;
		}
		logger.trace("input: arguments={}", Arrays.toString(arguments));
		if (arguments.length > 0) {
			switch (arguments[0].toLowerCase()) {
			case "-keyboard":
				logger.trace("Selecting DataFromKeyboardProvider.");
				return new DataFromKeyboardProvider();
			case "-code":
				logger.trace("Selecting DataHardcodedProvider");
				return new DataHardcodedProvider();
			case "-file":
				if (arguments.length > 1)
					if (arguments[1].endsWith(".xml") || (arguments[1].endsWith(".XML"))){
						logger.trace("Selecting DataFromJaxbProvider");
						return new DataFromJaxbProvider(arguments[1]);
					} else {
						logger.trace("Selecting DataFromFileProvider");
						return new DataFromFileProvider(arguments[1]);								
					}
				else {
					logger.error("Required argument after '-file' argument not found.");
					return null;
				}
			default:
				logger.error("Argument '{}' not recognized as valid argument.", arguments[0]);
				return null;
			}
		}
		return null;
	}

}