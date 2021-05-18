package com.shakal.imageapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakal.imageapi.contracts.service.ICreatureService;
import com.shakal.imageapi.dto.CreatureTokenDTO;
import com.shakal.imageapi.dto.create.CreatureTokenCreateDTO;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.filedata.service.ExternalCreatureProfileImageService;
import com.shakal.imageapi.mappers.CreatureMapper;
import com.shakal.imageapi.model.Creature;
//import com.shakal.imageapi.model.Creature;
import com.shakal.imageapi.repository.ICreatureDAO;
//import com.shakal.imageapi.repository.ImageTokenDAO;
import com.shakal.imageapi.utils.Messages;

@Service
public class CreatureService implements ICreatureService {
	
	//private ImageTokenDAO imageTokenDAO;
	private ICreatureDAO creatureDao;
	private ExternalCreatureProfileImageService externalProfileImageService;
	
	@Autowired
	public CreatureService(
			//ImageTokenDAO imageTokenDAO,
			ExternalCreatureProfileImageService externalProfileImageService,
			ICreatureDAO creatureDao) {
		//this.imageTokenDAO = imageTokenDAO;
		this.externalProfileImageService = externalProfileImageService;
		this.creatureDao = creatureDao;
		
	}

	@Override
	public CreatureTokenDTO getCreatureToken(long id) throws ResourceNotFoundException {
		/*
		ImageToken search = this.imageTokenDAO.retrieveCharacterTokenById(id)
					.orElseThrow(() -> new ResourceNotFoundException(Messages.CHARACTER_NOT_FOUND));
					/*
		/*
		if(search.getImagePath() == null || search.getImagePath().trim().length()  0 ) {
			CreatureTokenDTO result = new CreatureTokenDTO();
			result.setId(id);
			//result.setPicture(search.getCreature().getImagePath());
			result.setPicture(externalProfileImageService.retrieveFileById());
			return result;
		}else {
			return CreatureTokenMapper.mapEntityToDTO(search);
		}
		*/
		
		Creature search = this.creatureDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Messages.CHARACTER_NOT_FOUND));
		
		CreatureTokenDTO result = new CreatureTokenDTO();
		result.setId(id);
		//result.setPicture(search.getCreature().getImagePath());
		//result.setPicture(externalProfileImageService.retrieveFileById("creature89.jpg"));
		result.setPicture(this.externalProfileImageService.retrieveFileById(search.getImagePath()));
		return result;
				
		
	}

	@Override
	public boolean saveCreatureToken(CreatureTokenCreateDTO inputDto) {
		this.creatureDao.save(CreatureMapper.createDtoToEntity(inputDto));
		return true;
	}

}
