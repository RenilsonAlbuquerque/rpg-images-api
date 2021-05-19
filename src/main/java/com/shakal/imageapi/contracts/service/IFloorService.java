package com.shakal.imageapi.contracts.service;

import java.util.List;

import com.shakal.imageapi.dto.commons.NumberNumberDTO;
import com.shakal.imageapi.dto.create.FloorCreateDTO;
import com.shakal.imageapi.dto.info.FloorInfoDTO;
import com.shakal.imageapi.dto.info.MapWallsDTO;
import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;



public interface IFloorService {
	
	FloorInfoDTO getFloorById(long id) throws ResourceNotFoundException;
	boolean createFloor(Long floorId,List<FloorCreateDTO> floorCreateDto) throws FileManagementException;
	boolean updateFloorWallsImage(long floorId,List<MapWallsDTO> inputDto) throws ResourceNotFoundException,FileManagementException;
	List<NumberNumberDTO> getFloorsByPlaceId(long placeId)throws ResourceNotFoundException;
	Long getDefaultFloorIdByPlaceId(Long id);
}
