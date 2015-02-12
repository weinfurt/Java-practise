package com.ca.msm.weipe03.homework.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

@XmlRootElement
public class JaxbData {
	
	private SchoolClass schoolClass;
	private List<Bus> buses;

	public JaxbData() {
	}
	
	public SchoolClass getSchoolClass(){
		return this.schoolClass;
	}
	
	@XmlElement(name = "schoolclass")
	public void setSchoolClass(SchoolClass schoolClass){
		this.schoolClass = schoolClass;
	}
	
	public List<Bus> getBuses(){
		return this.buses;
	}
	
	@XmlElementWrapper(name = "buslist")
	@XmlElement(name = "bus")
	public void setBuses(List<Bus> buses){
		this.buses = buses;
	}
	

}
