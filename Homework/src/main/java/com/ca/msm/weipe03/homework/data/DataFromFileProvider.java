package com.ca.msm.weipe03.homework.data;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public class DataFromFileProvider implements IDataProvider {
	private static final Logger logger = LoggerFactory.getLogger(IDataProvider.class);

	private static Path filePath;
	private static final String PREFIX_CLASS = "class:";
	private static final String PREFIX_BUSES = "buses:";
	private static List<String> lines = new ArrayList<String>();

	public DataFromFileProvider(String inputFile) {
		logger.debug("DataFromFileProvider()");
		logger.trace("input: inputFile={}", inputFile);
		if(inputFile == null) {
			throw new IllegalArgumentException("Parameter 'inputFile' cannot be null");
		}
		InputStream stream = getClass().getResourceAsStream("/"+inputFile);
		if (stream == null){
			throw new IllegalStateException("Unable to create stream from file.");
		}
		Scanner scan = new Scanner(stream);
		while (scan.hasNextLine()){
			lines.add(scan.nextLine());
		}
		scan.close();
		logger.trace("File content: {}", lines.toString());
	}
	
	@Override
	public List<Bus> provideBuses() throws CloneNotSupportedException {
		logger.debug("provideBuses()");
		String busName = "";
		int seats = 0;
		List<Bus> buses = new ArrayList<Bus>();
		if (lines == null){
			logger.warn("The input file does not contain any lines.");
			return buses;
		}	
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

	@Override
	public SchoolClass provideSchoolClass() throws CloneNotSupportedException {
		logger.debug("provideSchoolClass()");
		String className = "";
		int studentsCount = 0;
		if (lines != null) {
			for (String line : lines) {
				if (line.startsWith(PREFIX_CLASS)) {
					String[] token = line.split("\\s");
					if (token.length >= 3) {
						className = token[1];
						try {
							studentsCount = Integer.parseInt(token[2]);
						} catch (NumberFormatException e) {
							logger.error("\"{}\" is not an integer number.", token[2]);
						}
						logger.trace("New record parsed: className={}, students={}", className, studentsCount);
					}
				}
			}
			if (className.equals("") && studentsCount == 0) {
				logger.warn("There is no record of a school class in data file \"{}\".", filePath);
			}
		} else {
			logger.warn("The input file does not contain any lines.");
		}
		return new SchoolClass.Builder(studentsCount).withName(className).build();
	}
}
