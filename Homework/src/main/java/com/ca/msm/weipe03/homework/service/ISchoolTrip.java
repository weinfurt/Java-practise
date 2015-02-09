package com.ca.msm.weipe03.homework.service;

import java.util.List;

import com.ca.msm.weipe03.homework.entity.Bus;
import com.ca.msm.weipe03.homework.entity.SchoolClass;

public interface ISchoolTrip {
	SchoolTripResult tripResult(SchoolClass schoolClass, List<Bus> buses); 
}
