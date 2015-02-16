package com.ca.msm.weipe03.homework.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

/**
 * Data provider which reads plain text file with defined format of lines.
 * @author weipe03
 *
 */
public class DataFromFileProvider implements IDataProvider {
	private static final Logger logger = LoggerFactory.getLogger(IDataProvider.class);

	private String filePath;
	private static final String PREFIX_CLASS = "class:";
	private static final String PREFIX_BUSES = "buses:";

	/**
	 * Constructor
	 * @param inputFile - name of the file located in resources
	 * @throws DataReadException
	 */
	public DataFromFileProvider(String inputFile) throws DataReadException{
		logger.trace("DataFromFileProvider()");
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
	}

	/**
	 * Parse the text file to the collection of separated lines
	 * @return collection of String lines
	 * @throws DataReadException
	 */
	private List<String> parseFileToLines() throws DataReadException{
		logger.trace("parseFileToLines()");
		List<String> lines = new ArrayList<String>();
		Scanner scan;
		try {
			scan = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			logger.error("Cannot find the inout file '{}'.", filePath, e);
			throw new DataReadException("Cannot find the input file '" + filePath + "'.", e);
		}
		while (scan.hasNextLine()){
			lines.add(scan.nextLine());
		}
		scan.close();
		logger.trace("File content: {}", lines.toString());
		if (lines.isEmpty()){
			logger.error("The input file '{}' contains no data.", filePath);
			throw new DataReadException("The input file '"+filePath+"' contains no data.");
		}			
		return lines;
	}
		
	/* (non-Javadoc)
	 * @see com.ca.msm.weipe03.homework.data.IDataProvider#provideBuses()
	 */
	@Override
	public List<Bus> provideBuses() throws DataReadException{
		logger.trace("provideBuses()");
		List<String> lines = parseFileToLines();
		
		String busName = "";
		int seats = 0;
		List<Bus> buses = new ArrayList<Bus>();
		for (String line : lines){
			if (line.startsWith(PREFIX_BUSES)) {
				String[] token = line.split("\\s");
				if (token.length >= 3){
					for (int i = 1; i < token.length; i += 2){
						busName = token[i];
						try {
							seats = Integer.parseInt(token[i+1]);
						} catch (NumberFormatException e) {
							logger.error("\"{}\" is not an integer number.", token[i+1]);
						} catch (ArrayIndexOutOfBoundsException e) {
							logger.error("Missing last item in the record of available buses");
						}
						buses.add(new Bus.Builder(seats).withName(busName).build());
						busName = ""; seats = 0;
					}
				}
			}
		}
		if (buses.size() == 0){
			logger.warn("There is no record of available buses in data file \"{}\".", filePath);
		}
		logger.trace("output: buses={}", buses);
		return buses;
	}

	/* (non-Javadoc)
	 * @see com.ca.msm.weipe03.homework.data.IDataProvider#provideSchoolClass()
	 */
	@Override
	public SchoolClass provideSchoolClass() throws DataReadException {
		logger.trace("provideSchoolClass()");
		List<String> lines = parseFileToLines();

		String className = "";
		int studentsCount = 0;
		for (String line : lines) {
			if (line.startsWith(PREFIX_CLASS)) {
				String[] token = line.split("\\s");
				if (token.length >= 3) {
					className = token[1];
					try {
						studentsCount = Integer.parseInt(token[2]);
					} catch (NumberFormatException e) {
						logger.error(
								"The string \"{}\" does not represent an integer value.",
								token[2]);
						throw new DataReadException("The string \"" + token[2]
								+ "\" does not represent an integer value.", e);
					}
					logger.trace(
							"New record parsed: className={}, students={}",
							className, studentsCount);
				}
			}
		}
		if (className.equals("") && studentsCount == 0) {
			logger.warn(
					"There is no record of a school class in data file \"{}\".",
					filePath);
		}
		return new SchoolClass.Builder(studentsCount).withName(className)
				.build();
	}
}
