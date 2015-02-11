package com.ca.msm.weipe03.homework.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a 'bus' entity. Immutable version.
 * 
 * @author weipe03
 *
 */

@XmlRootElement(namespace = "com.ca.msm.weipe03.homework.data.JaxbData")
public class Bus implements Cloneable{
	private static final Logger logger = LoggerFactory.getLogger(Bus.class);

	@XmlElement
	private int capacity; // required
	@XmlElement
	private String name;  // optional
	
	private static final int MIN_CAPACITY = 1;
	private static final int MAX_CAPACITY = 100;

	/**
	 * Private constructor
	 * 
	 * @param builder
	 */
	private Bus(){
	}
	
	public Bus(Bus obj){
		this.capacity = obj.getCapacity();
		this.name = obj.getName();
	}

	/**
	 * Get bus capacity
	 * 
	 * @return capacity of the bus
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Get bus name
	 * 
	 * @return name of the bus
	 */
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bus name: " + name + " (seats: " + capacity + ")";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bus other = (Bus) obj;
		if (capacity != other.capacity)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * Builder class for building the bus immutable object
	 * 
	 * @author weipe03
	 *
	 */
	public static class Builder{
		
		private Bus object = new Bus();
		
		/**
		 * constructor of the bus Builder
		 * 
		 * @param capacity (required) 
		 */
		public Builder(int capacity){
			object.capacity = capacity;
		}
		
		/**
		 * @param name (optional) - name of the bus
		 * 
		 * @return 
		 */
		public Builder withName(String name){
			object.name = name;
			return this;
		}
		
		public boolean validate(){
			logger.trace("validate()");
			if (object.capacity >= MIN_CAPACITY && object.capacity <= MAX_CAPACITY){
				logger.trace("The field 'capacity' is within the allowed range.");
				return true;
			} else {
				logger.error("The value "+object.capacity+" of the field 'capacity' is out of allowed range <"+MIN_CAPACITY+","+MAX_CAPACITY+">.");
				return false;
			}
		}
		
		/**
		 * Build the Bus object
		 * 
		 * @return
		 * @throws CloneNotSupportedException 
		 */
		public Bus build() {
			if (validate()) {
				try {
					return (Bus) object.clone();
				} catch (CloneNotSupportedException e) {
					logger.error("The object of the class "+object.getClass()+" does not support clone().", e);
				}
			}
			return null;
		}

	}
}