package com.shakal.imageapi.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakal.imageapi.contracts.service.ICreatureService;
import com.shakal.imageapi.dto.CreatureTokenDTO;
import com.shakal.imageapi.dto.GenericImageDTO;
import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.filedata.service.ExternalCreatureProfileImageService;
import com.shakal.imageapi.helpers.FileHelper;
import com.shakal.imageapi.model.Creature;
import com.shakal.imageapi.repository.ICreatureDAO;
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
	public boolean saveCreatureToken(GenericImageDTO inputDto) throws FileManagementException {
		
		Creature entityCreatureImage = new Creature();
		entityCreatureImage.setId(inputDto.getId());
		
		entityCreatureImage.setImagePath(
				this.saveCharacterProfilePicture(inputDto.getId(), inputDto.getImage())
				);
		this.creatureDao.save(entityCreatureImage);
		return true;
	}
	private String saveCharacterProfilePicture(long id,String base64Image) throws FileManagementException {
		String fileName = "map" + id + ".jpg";
		String fileIdentifier = null;
		try {
			File fileToUp = FileHelper.base64ToFile(base64Image);
			fileIdentifier = externalProfileImageService.saveMapImageFile(fileToUp, fileName);
			return fileIdentifier;
		} catch (IOException e) {
			throw new FileManagementException("Erro ao salvar o arquivo");
		}
	}

}
