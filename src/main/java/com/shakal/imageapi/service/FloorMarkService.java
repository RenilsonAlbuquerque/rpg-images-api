package com.shakal.imageapi.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakal.imageapi.contracts.service.IFloorMarkService;
import com.shakal.imageapi.dto.create.PlaceMarkCreateDTO;
import com.shakal.imageapi.dto.info.PlaceMarkInfo;
import com.shakal.imageapi.dto.overview.FloorMarkOverviewDTO;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.mappers.FloorMarkMapper;
import com.shakal.imageapi.model.floor.Floor;
import com.shakal.imageapi.model.floor.FloorMark;
import com.shakal.imageapi.repository.IFloorDAO;
import com.shakal.imageapi.repository.IFloorMarkDAO;
import com.shakal.imageapi.utils.Messages;



@Service
public class FloorMarkService implements IFloorMarkService {
	
	
	private IFloorMarkDAO placeMarkDao;
	private IFloorDAO floorDao;
	
	@Autowired
	public FloorMarkService(IFloorMarkDAO placeMarkDao,IFloorDAO floorDao) {
		this.placeMarkDao = placeMarkDao;
		this.floorDao = floorDao;
	}

	@Override
	public PlaceMarkInfo getPlaceMarkInfoById(long id) throws ResourceNotFoundException {
		FloorMark placeMark = this.placeMarkDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Messages.STORY_NOT_FOUND));
		return FloorMarkMapper.entityToInfo(placeMark);
		
		
	}

	@Override
	public List<FloorMarkOverviewDTO> getAllPlaceMarksOfPlace(long floorId) throws ResourceNotFoundException {
		Floor floor = this.floorDao.findById(floorId)
				.orElseThrow(() -> new ResourceNotFoundException(Messages.PLACE_NOT_FOUND));
		return floor.getMarks()
		.stream()
		.map(mark -> FloorMarkMapper.entityToOverview(mark))
        .collect(Collectors.toList());
	}

	@Override
	public PlaceMarkCreateDTO createFloorMark(PlaceMarkCreateDTO placeMarkCreateDTO) throws ResourceNotFoundException {
		Floor floor = this.floorDao.findById(placeMarkCreateDTO.getPlaceId())
				.orElseThrow(() -> new ResourceNotFoundException(Messages.PLACE_NOT_FOUND));
		FloorMark entity = new FloorMark();
		entity.setName(placeMarkCreateDTO.getName());
		entity.setDescription(placeMarkCreateDTO.getDescription());
		entity.setCoordinateX(placeMarkCreateDTO.getCoordinateX());
		entity.setCoordinateY(placeMarkCreateDTO.getCoordinateY());
		entity.setFloor(floor);
		this.placeMarkDao.save(entity);
		return placeMarkCreateDTO;
	}
	
	

}
