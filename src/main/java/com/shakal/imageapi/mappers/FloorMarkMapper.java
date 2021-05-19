package com.shakal.imageapi.mappers;

import com.shakal.imageapi.dto.info.PlaceMarkInfo;
import com.shakal.imageapi.dto.overview.FloorMarkOverviewDTO;
import com.shakal.imageapi.model.floor.FloorMark;

public class FloorMarkMapper {

	public static PlaceMarkInfo entityToInfo(FloorMark entity) {
		PlaceMarkInfo result = new PlaceMarkInfo();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setDescription(entity.getDescription());
		return result;
		
	}
	public static FloorMarkOverviewDTO entityToOverview(FloorMark entity) {
		FloorMarkOverviewDTO result = new FloorMarkOverviewDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setCoordinateX(entity.getCoordinateX());
		result.setCoordinateY(entity.getCoordinateY());
		return result;
	}
}
