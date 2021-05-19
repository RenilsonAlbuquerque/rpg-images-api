package com.shakal.imageapi.service;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shakal.imageapi.contracts.service.IFloorService;
import com.shakal.imageapi.dto.commons.NumberNumberDTO;
import com.shakal.imageapi.dto.create.FloorCreateDTO;
import com.shakal.imageapi.dto.info.FloorInfoDTO;
import com.shakal.imageapi.dto.info.MapWallsDTO;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.filedata.service.ExternalFloorImageService;
import com.shakal.imageapi.model.floor.Floor;
import com.shakal.imageapi.repository.IFloorDAO;
import com.shakal.imageapi.repository.IFloorImageDAO;
import com.shakal.imageapi.repository.IFloorWallDAO;
import com.shakal.imageapi.utils.Messages;
import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.helpers.FileHelper;
import com.shakal.imageapi.mappers.FloorMapper;
import com.shakal.imageapi.mappers.FloorWallsMapper;

@Service
public class FloorService implements IFloorService {
	
	private IFloorDAO floorRepository;
	private IFloorWallDAO floorWallsDAO;
	private IFloorImageDAO floorDAO;
	private ExternalFloorImageService externalFloorImageService;
	
	@Autowired
	public FloorService(IFloorImageDAO floorDAO,
			ExternalFloorImageService externalFloorImageService,
			IFloorDAO floorRepository,
			IFloorWallDAO floorWallsDAO) {
		this.floorDAO = floorDAO;
		this.externalFloorImageService = externalFloorImageService;
		this.floorRepository = floorRepository;
		this.floorWallsDAO = floorWallsDAO;
		
	}
	/*
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
	*/
	/*
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
	*/
	@Override
	public FloorInfoDTO getFloorById(long id) throws ResourceNotFoundException {
		Floor floor = this.floorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Messages.FLOOR_NOT_FOUND));
		
		floor.setMap(this.externalFloorImageService.retrieveFileById(floor.getMap()));
		
		return FloorMapper.floorEntityToDto(floor);
	}
	@Override
	public boolean createFloor(Long placeId,List<FloorCreateDTO> floorCreateDto) throws FileManagementException {
		for(FloorCreateDTO floorDto: floorCreateDto) {
			Floor entity = new Floor();
			
			entity.setMap("");
			
			entity.setSquareDimension(floorDto.getSquareDimension());
			entity.setSquareSizeCm(floorDto.getSquareDimensionCm());
			entity.setNaturalHeight(200d);
			entity.setNaturalWidth(200d);
			entity.setFloorOrder(floorDto.getFloorOrder());

			
			entity.setPlaceId(placeId);
			entity = this.floorRepository.save(entity);
			
			String fileName = "map" + entity.getId()+ ".jpg";
			Dimension dimension = new Dimension();
			String fileIdentifier = null;
			
			try {
				File fileToUp = FileHelper.base64ToFile(floorDto.getMap());
				fileIdentifier = externalFloorImageService.saveFloorImageFile(fileToUp, fileName);
				dimension = FileHelper.getDimensionsOfImage(fileToUp);
				entity.setMap(fileIdentifier);
				entity.setNaturalHeight(dimension.getHeight());
				entity.setNaturalWidth(dimension.getWidth());
				entity = this.floorRepository.save(entity);
				
			} catch (IOException e) {
				throw new FileManagementException("Erro ao salvar o arquivo");
			}
			
		}
		
		return true;
		
	}
	@Override
	public boolean updateFloorWallsImage(long floorId, List<MapWallsDTO> inputDto)
			throws ResourceNotFoundException, FileManagementException {
		Floor floor = this.floorRepository.findById(floorId)
				.orElseThrow(() -> new ResourceNotFoundException(Messages.PLACE_NOT_FOUND));
		
		this.floorWallsDAO.deletePlacesWallsByFloorId(floorId);
		
		for(MapWallsDTO wall: inputDto) {
			this.floorWallsDAO.save( FloorWallsMapper.dtoToEntity(wall,floor));
		}
		return true;
	}
	@Override
	public List<NumberNumberDTO> getFloorsByPlaceId(long placeId) throws ResourceNotFoundException {
		return this.floorRepository.getFloorsByPlaceId(placeId).stream()
				.map(floor ->  new NumberNumberDTO(floor.getFloorOrder(),floor.getId()))
				 .collect(Collectors.toList());
	}
	@Override
	public Long getDefaultFloorIdByPlaceId(Long placeId) {
		List<Floor> result = this.floorRepository.getFloorsByPlaceId(placeId);
		return (result.size() > 0 ) ? result.get(0).getId() : 0 ;
	}

	
}
