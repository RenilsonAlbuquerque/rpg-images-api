package com.shakal.imageapi.contracts.service;

import com.shakal.imageapi.dto.GenericImageDTO;
import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;

public interface IFloorService {
	boolean saveFloor(GenericImageDTO inputDto) throws FileManagementException ;
	GenericImageDTO getFloorImageToken(long id) throws ResourceNotFoundException;
}
