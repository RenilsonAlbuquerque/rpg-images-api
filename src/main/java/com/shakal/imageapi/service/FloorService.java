package com.shakal.imageapi.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shakal.imageapi.contracts.service.IFloorService;
import com.shakal.imageapi.dto.FloorDetailDTO;
import com.shakal.imageapi.dto.GenericImageDTO;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.filedata.service.ExternalFloorImageService;
import com.shakal.imageapi.model.floor.FloorImage;
import com.shakal.imageapi.repository.IFloorDAO;
import com.shakal.imageapi.utils.Messages;
import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.helpers.FileHelper;

@Service
public class FloorService implements IFloorService {
	
	private IFloorDAO floorDAO;
	private ExternalFloorImageService externalFloorImageService;
	
	@Autowired
	public FloorService(IFloorDAO floorDAO,
			ExternalFloorImageService externalFloorImageService) {
		this.floorDAO = floorDAO;
		this.externalFloorImageService = externalFloorImageService;
		
	}
	@Override
	public boolean saveFloor(FloorDetailDTO inputDto) throws FileManagementException {
		FloorImage entityFloorImage = new FloorImage();
		entityFloorImage.setId(inputDto.getId());
		
		entityFloorImage.setImagePath(
				this.saveCharacterProfilePicture(inputDto.getId(), inputDto.getMap())
				);
		entityFloorImage.setSquareSize(inputDto.getSquareSize());
		this.floorDAO.save(entityFloorImage);
		return true;
	}
	
	@Override
	public FloorDetailDTO getFloorImageToken(long id) throws ResourceNotFoundException {
		FloorImage search = this.floorDAO.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Messages.FLOOR_NOT_FOUND));
		FloorDetailDTO result = new FloorDetailDTO();
		result.setId(id);
		result.setMap(this.externalFloorImageService.retrieveFileById(search.getImagePath()));
		result.setSquareSize(search.getSquareSize());
		return result;
	}
	private String saveCharacterProfilePicture(long id,String base64Image) throws FileManagementException {
		String fileName = "map" + id + ".jpg";
		String fileIdentifier = null;
		try {
			File fileToUp = FileHelper.base64ToFile(base64Image);
			fileIdentifier = externalFloorImageService.saveFloorImageFile(fileToUp, fileName);
			return fileIdentifier;
		} catch (IOException e) {
			throw new FileManagementException("Erro ao salvar o arquivo");
		}
	}
	

	
}
