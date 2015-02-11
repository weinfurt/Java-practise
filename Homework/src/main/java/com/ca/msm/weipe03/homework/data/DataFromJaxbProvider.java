package com.ca.msm.weipe03.homework.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public class DataFromJaxbProvider implements IDataProvider{
	private static final Logger logger = LoggerFactory.getLogger(IDataProvider.class);
	
	private String filePath;
	private JaxbData jaxbDataObject;

	public DataFromJaxbProvider(String inputFile) throws DataReadException {
		logger.trace("DataFromJaxbProvider()");
		logger.trace("input: inputFile={}", inputFile);
		if(inputFile == null) {
			logger.error("Parameter 'inputFile' cannot be null");
			throw new IllegalArgumentException("Parameter 'inputFile' cannot be null");
		}
		try {
			filePath = getClass().getResource("/" + inputFile).getFile();
		} catch (NullPointerException e) {
			logger.error("Cannot locate the '{}' input file in resources.", inputFile);
			throw new DataReadException("Cannot locate the "+inputFile+" input file in resources.");
		}
		if (filePath.startsWith("/"))
			filePath = filePath.substring(1);
		logger.trace("Full path to input file: {}", filePath);
		unmarshalData();
	}
	
	private void unmarshalData() throws DataReadException{
		logger.trace("unmarshalData()");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(JaxbData.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbDataObject = (JaxbData) jaxbUnmarshaller.unmarshal(new File(filePath));
		} catch (JAXBException e) {
			logger.error("JAXB error occured.", e);
			throw new DataReadException("JAXB error occured.", e);
		} catch (NullPointerException e) {
			logger.error("Cannot find the inout file '{}'.", filePath, e);
			throw new DataReadException("Cannot find the input file '" + filePath + "'.", e);
			
		}

	}

	@Override
	public List<Bus> provideBuses(){
		List<Bus> buses = new ArrayList<Bus>(jaxbDataObject.getBuses());
		return buses;
	}

	@Override
	public SchoolClass provideSchoolClass(){
		SchoolClass schoolClass = new SchoolClass(jaxbDataObject.getSchoolClass());
		return schoolClass;
	}

}
