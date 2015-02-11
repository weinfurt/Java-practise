package com.ca.msm.weipe03.homework.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public class DataFromKeyboardProvider implements IDataProvider{

	public DataFromKeyboardProvider() {
		// TODO Auto-generated constructor stub
	}
	
	protected String readStringFromKeyboard(String prompt) {
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(prompt);
		try {
			input = br.readLine();
		} catch (IOException e) {
			System.out.println("I/O error occured when reading your input.");
			e.printStackTrace();
		}
		return input;
	}
	
	protected int readIntFromKeyboard(String prompt) {
		int number = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(prompt);
		try {
			String input = br.readLine();
			number = Integer.parseInt(input);
		} catch (IOException e) {
			System.out.println("I/O error occured when reading your input.");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Write an integer number please");
			// e.printStackTrace();
		}
		return number;
	}
	
	@Override
	public List<Bus> provideBuses(){
		List<Bus> list = new ArrayList<Bus>();

		int busesCount = readIntFromKeyboard("Enter the number of available buses >");
		
		for (int i = 1; i <= busesCount; i++){
			String busName = readStringFromKeyboard("   Bus #"+i+": Enter the bus name >");
			int busCapacity = readIntFromKeyboard("   Bus #"+i+": Enter the number of seats >");
			list.add(new Bus.Builder(busCapacity).withName(busName).build());
		}
		return list;		
	}
	@Override
	public SchoolClass provideSchoolClass(){
		String className = readStringFromKeyboard("Enter the class name >");
		int studentsCount = readIntFromKeyboard("Enter the number of students in class >");
		return new SchoolClass.Builder(studentsCount).withName(className).build();	
	}

}
