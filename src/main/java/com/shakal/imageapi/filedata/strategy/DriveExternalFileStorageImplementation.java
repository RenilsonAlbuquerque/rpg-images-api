package com.shakal.imageapi.filedata.strategy;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.filedata.enums.PathEnum;
import com.shakal.imageapi.filedata.repository.GoogleDriveRepository;
import com.shakal.imageapi.helpers.FileHelper;

public class DriveExternalFileStorageImplementation implements IExternalFileStorageStrategy {

	private final String JPEG_TYPE = "image/jpeg";
	
	@Override
	public String saveFile(File file, String fileName,String filePath) throws FileManagementException {
		try {
			return GoogleDriveRepository.saveMapImageFile(file, fileName,JPEG_TYPE,filePath);
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
			throw new FileManagementException("Erro ao submeter o arquivo");
		}
	}

	@Override
	public String retrieveFileById(String id,String imagePath) throws ResourceNotFoundException {
		try {
			return GoogleDriveRepository.retrieveFileById(id);
		} catch (IOException | GeneralSecurityException e) {
			throw new ResourceNotFoundException("Arquivo nao encontrado");
		}
	}

	@Override
	public String retrieveMinimap(String fileId,String imagePath) {
		
		BufferedImage img;
		BufferedImage scaledImage;
		byte[] imageBytes;
		String result = "";
		try {
			img = FileHelper.byteArrayOSToBufferedImage(GoogleDriveRepository.retrieveFileOutputStreamById(fileId));
		
			scaledImage = FileHelper.resiezeImage(img,img.getWidth()/10, img.getHeight()/10);
		
			ByteArrayOutputStream scaledImageOs = new ByteArrayOutputStream();
		
			ImageIO.write(scaledImage, "jpeg", scaledImageOs);
			imageBytes = scaledImageOs.toByteArray();
        
			result = "data:image/jpeg;base64," + Base64
		          .getEncoder()
		          .encodeToString(imageBytes);
		} catch (IOException | GeneralSecurityException e) {
			result = "";
		}
		return result;
		
	}

	@Override
	public String getPath(PathEnum pathSelector) {
		String result = "";
		if(pathSelector == PathEnum.MAP) {
			result = "1-QBNuMNAdlvuflChf9sjGwm9_iqNlkFs";
		}
		if(pathSelector == PathEnum.PROFILE_PICTURE) {
			result = "102AY_xfMCBWDG4-8-TNQ_G2_HdHHenUa";
		}
		if(pathSelector == PathEnum.TOKEN) {
			result = "1OW5epKZwJt-0Pq90dbOaSthccmgnzvfU";
		}
		return result;
	}
}
