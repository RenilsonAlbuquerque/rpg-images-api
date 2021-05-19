package com.shakal.imageapi.contracts.service;

import java.util.List;

import com.shakal.imageapi.dto.create.PlaceMarkCreateDTO;
import com.shakal.imageapi.dto.info.PlaceMarkInfo;
import com.shakal.imageapi.dto.overview.FloorMarkOverviewDTO;
import com.shakal.imageapi.exception.ResourceNotFoundException;



public interface IFloorMarkService {

	PlaceMarkInfo getPlaceMarkInfoById(long id) throws ResourceNotFoundException;
	List<FloorMarkOverviewDTO> getAllPlaceMarksOfPlace(long placeId) throws ResourceNotFoundException;
	PlaceMarkCreateDTO createFloorMark(PlaceMarkCreateDTO placeMarkCreateDTO) throws ResourceNotFoundException;
}
