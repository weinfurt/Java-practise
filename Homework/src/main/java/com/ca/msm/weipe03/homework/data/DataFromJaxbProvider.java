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
	
	private JaxbData jaxbDataObject;

	public DataFromJaxbProvider(String inputFile) {
		logger.debug("DataFromJaxbProvider()");
		logger.trace("input: inputFile={}", inputFile);
		if(inputFile == null) {
			throw new IllegalArgumentException("Parameter 'inputFile' cannot be null");
		}
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(JaxbData.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			File XMLfile = new File(getClass().getResource("/" + inputFile).getFile());
			jaxbDataObject = (JaxbData) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			logger.error("JAXB Error occured.", e);
		} catch (NullPointerException e) {
			logger.error("Unable to find the input file \"{}\".", inputFile, e);
		}

	}

	@Override
	public List<Bus> provideBuses() throws CloneNotSupportedException {
		List<Bus> buses = new ArrayList<Bus>(jaxbDataObject.getBuses());
		return buses;
	}

	@Override
	public SchoolClass provideSchoolClass() throws CloneNotSupportedException {
		SchoolClass schoolClass = new SchoolClass(jaxbDataObject.getSchoolClass());
		return schoolClass;
	}

}
