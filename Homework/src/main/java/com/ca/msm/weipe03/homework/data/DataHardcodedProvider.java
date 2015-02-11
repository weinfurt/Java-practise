package com.ca.msm.weipe03.homework.data;

import java.util.ArrayList;
import java.util.List;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public class DataHardcodedProvider implements IDataProvider{

	public DataHardcodedProvider() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<Bus> provideBuses(){
		List<Bus> list = new ArrayList<Bus>();
		list.add(new Bus.Builder(49).withName("Renault").build());
		list.add(new Bus.Builder(66).withName("Scania").build());
		list.add(new Bus.Builder(48).withName("Man").build());
		list.add(new Bus.Builder(10).withName("Transporter").build());
		list.add(new Bus.Builder(14).withName("MiniBus").build());
		list.add(new Bus.Builder(75).withName("Karosa").build());
		return list;
	}
	
	@Override
	public SchoolClass provideSchoolClass(){
		return new SchoolClass.Builder(74).withName("Kvarta B").build();		
	}

}
