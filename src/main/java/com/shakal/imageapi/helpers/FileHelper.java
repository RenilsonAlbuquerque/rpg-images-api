package com.shakal.imageapi.helpers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.imageio.ImageIO;



//import org.apache.tomcat.util.codec.binary.Base64;

public abstract class FileHelper {
	
	public static File base64ToFile(String base64File) throws IOException {
		byte[] decodedImg =
				//Base64.getDecoder().decode(base64File.getBytes("UTF-8"));
				Base64.getMimeDecoder().decode(base64File.split(",")[1]);
		File fileToBeSent = java.io.File.createTempFile("imagefile", ".tmp");
		
		try (FileOutputStream stream = new FileOutputStream(fileToBeSent)) {
		    stream.write(decodedImg);
		}
		return fileToBeSent;
	}
	public static Dimension createFileAndPersist(String pathName,String base64File, String fileName) throws IOException {
		
		byte[] decodedImg =
				//Base64.getDecoder().decode(base64File.getBytes("UTF-8"));
				Base64.getMimeDecoder().decode(base64File.split(",")[1]);
				
		Path destinationFile = Paths.get(pathName,fileName);
		Files.write(destinationFile, decodedImg);
		
		return getDimensionsOfImage(new File(pathName+ fileName));
	}
	public static String convertImageToBase64(String filePath) throws IOException {
		File file = new File(filePath);
		return "data:image/jpeg;base64," + Base64
		          .getEncoder()
		          .encodeToString(Files.readAllBytes(file.toPath()));

	}
	public static Dimension getDimensionsOfImage(File file) {
		BufferedImage readImage = null;
		int w = 0, h = 0;
		try {
		    readImage = ImageIO.read(file);
		    h = readImage.getHeight();
		    w = readImage.getWidth();
		} catch (Exception e) {
		    readImage = null;
		}
		return new Dimension(w,h);
	}
	
	public static String convertFileImageToBase64Mini(String filePath)  {
		
		BufferedImage img;
		BufferedImage scaledImage;
		byte[] imageBytes;
		String result = "";
		try {
			img = ImageIO.read(new File(filePath));
		
			scaledImage = resiezeImage(img,img.getWidth()/10, img.getHeight()/10);
		
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
	public static String convertByteArrayImageToBase64Mini(ByteArrayOutputStream outputStream)  {
		
		BufferedImage img;
		BufferedImage scaledImage;
		byte[] imageBytes;
		String result = "";
		try {
			img = byteArrayOSToBufferedImage(outputStream);
		
			scaledImage = resiezeImage(img,img.getWidth()/10, img.getHeight()/10);
		
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
	public static BufferedImage resiezeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}
	public static BufferedImage byteArrayOSToBufferedImage(ByteArrayOutputStream byteArrayOs) throws IOException {
		return ImageIO.read(new ByteArrayInputStream(byteArrayOs.toByteArray()));
       
	}
}
