package com.shakal.imageapi.contracts.service;

import com.shakal.imageapi.dto.CreatureTokenDTO;
import com.shakal.imageapi.dto.create.CreatureTokenCreateDTO;
import com.shakal.imageapi.exception.ResourceNotFoundException;

public interface ICreatureService {
	
	CreatureTokenDTO getCreatureToken(long id) throws ResourceNotFoundException;
	boolean saveCreatureToken(CreatureTokenCreateDTO inputDto);

}
