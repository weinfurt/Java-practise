package com.ca.msm.weipe03.homework.data;

import java.util.List;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

/**
 * Data provider interface
 * @author weipe03
 *
 */
public interface IDataProvider {
	/**
	 * Method to provide collection of bus objects
	 * @return collection of bus objects
	 * @throws DataReadException
	 */
	List<Bus> provideBuses() throws DataReadException;
	/**
	 * Method to provide school class object
	 * @return school class object
	 * @throws DataReadException
	 */
	SchoolClass provideSchoolClass() throws DataReadException;

}
