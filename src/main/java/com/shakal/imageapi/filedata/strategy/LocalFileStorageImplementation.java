package com.shakal.imageapi.filedata.strategy;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.filedata.enums.PathEnum;
import com.shakal.imageapi.helpers.FileHelper;

public class LocalFileStorageImplementation  implements IExternalFileStorageStrategy {

	@Override
	public String retrieveFileById(String id,String filePath) throws ResourceNotFoundException {
		try {
			File file = new File(filePath + id);
			return "data:image/jpeg;base64," + Base64
			          .getEncoder()
			          .encodeToString(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			throw new ResourceNotFoundException("Arquivo nao encontrado");
		}
	}

	@Override
	public String saveFile(File file, String fileName, String filePath) throws FileManagementException {
		Path destinationFile = Paths.get(filePath,fileName);
		try {
			Files.write(destinationFile, Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			throw new FileManagementException("Erro ao salvar o arquivo");
		}
		
		return fileName;
	}

	@Override
	public String retrieveMinimap(String fileId,String filePath) {
		BufferedImage img;
		BufferedImage scaledImage;
		byte[] imageBytes;
		String result = "";
		try {
			img = ImageIO.read(new File(filePath + fileId));
		
			scaledImage = FileHelper.resiezeImage(img,img.getWidth()/10, img.getHeight()/10);
		
		ByteArrayOutputStream scaledImageOs = new ByteArrayOutputStream();
		
		ImageIO.write(scaledImage, "jpeg", scaledImageOs);
		imageBytes = scaledImageOs.toByteArray();
        
		result = "data:image/jpeg;base64," + Base64
		          .getEncoder()
		          .encodeToString(imageBytes);
		} catch (IOException e) {
			result = "";
		}
		return result;
	}

	@Override
	public String getPath(PathEnum pathSelector) {
		String result = "";
		if(pathSelector == PathEnum.MAP ) {
			result = "./images/maps/";
		}
		if(pathSelector == PathEnum.PROFILE_PICTURE ) {
			result = "./images/creature/profile/";
		}
		if(pathSelector == PathEnum.TOKEN) {
			result = "./images/creature/token/";
		}
		return result;
	}

}
