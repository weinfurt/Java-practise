package com.ca.msm.weipe03.homework.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

/**
 * Data provider which reads data from standard input (keyboard)
 * @author weipe03
 *
 */
public class DataFromKeyboardProvider implements IDataProvider{
	
	private static final Logger logger = LoggerFactory.getLogger(DataFromKeyboardProvider.class);
	private static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Empty constructor
	 */
	public DataFromKeyboardProvider() {
	}
	
	/**
	 * Method to read any string from the BufferedReader. 
	 * @param prompt - String which will be displayed as prompt on standard output
	 * @param br - BufferedReader to read from
	 * @return String of line which was written in BufferedReader
	 * @throws DataReadException
	 */
	private String readStringFromKeyboard(String prompt, BufferedReader br) throws DataReadException {
		String input = null;
		System.out.print(prompt);
		try {
			input = br.readLine();
		} catch (IOException e) {
			logger.error("I/O error occured when reading your input.", e);
			throw new DataReadException("I/O error occured when reading your input.", e);
		}
		return input;
	}
	
	/**
	 * Method to read integer value from the BufferedReader.
	 * @param prompt - String which will be displayed as prompt on standard output
	 * @param br - BufferedReader to read from
	 * @return Integer number which was written in BufferedReader
	 * @throws DataReadException
	 */
	private int readIntFromKeyboard(String prompt, BufferedReader br) throws DataReadException {
		int number = 0;
		System.out.print(prompt);
		String input = null;
		try {
			input = br.readLine();
			number = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			logger.error("The string '{}' cannot be converted to integer.", input, e);
			throw new DataReadException("The string '"+input+"' cannot be converted to integer.", e);
		} catch (IOException e) {
			logger.error("I/O error occured when reading your input.", e);
			throw new DataReadException("I/O error occured when reading your input.", e);
		}
		return number;
	}
		
	/**
	 * Method to get the Collection of Bus objects from the set of inputs in the BufferedReader
	 * @param br - BufferedReader to read from
	 * @return Collection of Bus objects
	 * @throws DataReadException
	 */
	protected List<Bus> provideBuses(BufferedReader br) throws DataReadException{
		List<Bus> list = new ArrayList<Bus>();
		int busesCount = readIntFromKeyboard("Enter the number of available buses >", br);
		for (int i = 1; i <= busesCount; i++){
			String busName = readStringFromKeyboard("   Bus #"+i+": Enter the bus name >", br);
			int busCapacity = readIntFromKeyboard("   Bus #"+i+": Enter the number of seats >", br);
			list.add(new Bus.Builder(busCapacity).withName(busName).build());
		}
		return list;		
	}
	
	/**
	 * Method to get the SchoolClass object from the set of inputs in the BufferedReader
	 * @param br - BufferedReader to read from
	 * @return SchoolClass object
	 * @throws DataReadException
	 */
	protected SchoolClass provideSchoolClass(BufferedReader br) throws DataReadException{
		String className = readStringFromKeyboard("Enter the class name >", br);
		int studentsCount = readIntFromKeyboard("Enter the number of students in class >", br);
		return new SchoolClass.Builder(studentsCount).withName(className).build();	
	}

	/* (non-Javadoc)
	 * @see com.ca.msm.weipe03.homework.data.IDataProvider#provideBuses()
	 */
	@Override
	public List<Bus> provideBuses() throws DataReadException{
		List<Bus> result = provideBuses(inputReader);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.ca.msm.weipe03.homework.data.IDataProvider#provideSchoolClass()
	 */
	@Override
	public SchoolClass provideSchoolClass() throws DataReadException{
		SchoolClass result = provideSchoolClass(inputReader);
		return result;		
	}
}
