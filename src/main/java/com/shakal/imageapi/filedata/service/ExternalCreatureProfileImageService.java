package com.shakal.imageapi.filedata.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.filedata.enums.PathEnum;
import com.shakal.imageapi.filedata.strategy.IExternalFileStorageStrategy;

@Service
public class ExternalCreatureProfileImageService {

	@Autowired
	private IExternalFileStorageStrategy externalFileStorage;
	private PathEnum businessPath = PathEnum.PROFILE_PICTURE;
	
	
	public String saveMapImageFile(File file, String fileName) throws FileManagementException {
		return this.externalFileStorage.saveFile(file, fileName,this.externalFileStorage.getPath(businessPath));
	}

	
	public String retrieveFileById(String id) throws ResourceNotFoundException {
		return this.externalFileStorage.retrieveFileById(id, this.externalFileStorage.getPath(businessPath));
	}

	
	public String retrieveMinimap(String fileId) throws ResourceNotFoundException {
		
		return this.externalFileStorage.retrieveMinimap(fileId, this.externalFileStorage.retrieveMinimap(fileId, this.externalFileStorage.getPath(businessPath))); 
		
	}
}
