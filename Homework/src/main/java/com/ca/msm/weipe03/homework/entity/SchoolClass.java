package com.ca.msm.weipe03.homework.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a 'school class' entity. Immutable version.
 * 
 * @author weipe03
 *
 */
@XmlRootElement(namespace = "com.ca.msm.weipe03.homework.data.JaxbData")
public class SchoolClass implements Cloneable{
	private static final Logger logger = LoggerFactory.getLogger(SchoolClass.class);

	@XmlElement
	private int numberOfStudents; // required
	@XmlElement
	private String className;     // optional
	
	private static final int MIN_STUDENTS = 1;
	private static final int MAX_STUDENTS = 200;

	private SchoolClass(){
	}
	public SchoolClass(SchoolClass obj){
		this.numberOfStudents = obj.getStudentsCount();
		this.className = obj.getClassName();
	}
	public int getStudentsCount(){
		return this.numberOfStudents;
	}

	public String getClassName(){
		return this.className;
	}

	@Override
	public String toString(){ 
		return "Class name: "+className+" (students: "+numberOfStudents+")";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + numberOfStudents;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolClass other = (SchoolClass) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (numberOfStudents != other.numberOfStudents)
			return false;
		return true;
	}
	
	public static class Builder{
		
		private SchoolClass object = new SchoolClass();
		
		public Builder(int numberOfStudents){
			object.numberOfStudents = numberOfStudents;
		}
		
		public Builder withName(String className){
			object.className = className;
			return this;
		}
		
		public boolean validate(){
			logger.trace("validate()");
			if (object.numberOfStudents >= MIN_STUDENTS && object.numberOfStudents <= MAX_STUDENTS){
				logger.trace("The field 'numberOfStudents' is within the allowed range.");
				return true;
			} else {
				logger.error("The value "+object.numberOfStudents+" of the field 'numberOfStudents' is out of allowed range <"+MIN_STUDENTS+","+MAX_STUDENTS+">.");
				return false;
			}
		}
		
		public SchoolClass build(){
			if (validate()) {
				try {
					return (SchoolClass) object.clone();
				} catch (CloneNotSupportedException e) {
					logger.error("The object of the class "+object.getClass()+" does not support clone().", e);
				}
			}
			return null;
		}
	}
}
