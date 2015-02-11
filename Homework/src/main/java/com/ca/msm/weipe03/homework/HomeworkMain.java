package com.ca.msm.weipe03.homework;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.msm.weipe03.homework.data.DataProviderFactory;
import com.ca.msm.weipe03.homework.data.DataReadException;
import com.ca.msm.weipe03.homework.data.IDataProvider;
import com.ca.msm.weipe03.homework.data.JaxbData;
import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;
import com.ca.msm.weipe03.homework.service.ISchoolTrip;
import com.ca.msm.weipe03.homework.service.SchoolTrip;
import com.ca.msm.weipe03.homework.service.SchoolTripResult;


public class HomeworkMain {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeworkMain.class);

	private ISchoolTrip trip;
	
	public HomeworkMain(){
		trip = new SchoolTrip();
	}
/*	
	private void saveToJaxbXML(IDataProvider provider) throws CloneNotSupportedException{
		JaxbData object = new JaxbData();
		object.setBuses(provider.provideBuses());
		object.setSchoolClass(provider.provideSchoolClass());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(JaxbData.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File XMLfile = new File("C:\\test.xml");
			jaxbMarshaller.marshal(object, XMLfile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
*/
	public void run(String args[]){
		logger.trace("Input arguments: {}", Arrays.toString(args));
		IDataProvider dataProvider = null;
		try {
			dataProvider = DataProviderFactory.getDataProvider(args);
		} catch (DataReadException e1) {
			logger.error("Cannot create data provider with following arguments: {}", Arrays.toString(args), e1);
		}
		if (dataProvider != null) {
//			saveToJaxbXML(dataProvider);
			try {
				displayResults(dataProvider.provideSchoolClass(), dataProvider.provideBuses());
			} catch (DataReadException e) {
				logger.error("Error in data provider occured.", e);
			}
		} else
			logger.error("Invalid input arguments");
	}
	
	public void displayResults(SchoolClass schoolClass, List<Bus> buses){	
		logger.info("**** Planning school trip of \"{}\" with {} students ****", schoolClass.getClassName(), schoolClass.getStudentsCount());
		SchoolTripResult result = trip.tripResult(schoolClass, buses);
		if (result.hasEnoughCapacity())
			logger.info("YES, there is enough buses for the trip of \"{}\".", schoolClass.getClassName());
		else
			logger.info("NO, there is not enough buses for the trip of \"{}\".", schoolClass.getClassName());			
		logger.info("The class \"{}\" will take following buses:", schoolClass.getClassName());
		for (Bus item : result.getUsedBuses()){
			logger.info(item.toString());
		}
	}
	
	public static void main(String args[]){
		new HomeworkMain().run(args);
	}
}
