package com.shakal.imageapi.contracts.service;

import com.shakal.imageapi.dto.CreatureTokenDTO;
import com.shakal.imageapi.dto.GenericImageDTO;
import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;

public interface ICreatureService {
	
	CreatureTokenDTO getCreatureToken(long id) throws ResourceNotFoundException;
	boolean saveCreatureToken(GenericImageDTO inputDto) throws FileManagementException;

}
