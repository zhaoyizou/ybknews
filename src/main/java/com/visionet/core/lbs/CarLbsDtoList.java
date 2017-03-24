package com.visionet.core.lbs;

import java.util.ArrayList;
import java.util.List;

public class CarLbsDtoList {
	private List<CarLbsDto> cars = new ArrayList<CarLbsDto>();

	public List<CarLbsDto> getCars() {
		return cars;
	}

	public void setCars(List<CarLbsDto> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "CarLbsDtoList [cars=" + cars + "]";
	}
	
}
