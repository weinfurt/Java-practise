package com.ca.msm.weipe03.homework.data;

import java.util.List;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public interface IDataProvider {
	List<Bus> provideBuses() throws DataReadException;
	SchoolClass provideSchoolClass() throws DataReadException;

}
