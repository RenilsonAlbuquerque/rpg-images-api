package com.shakal.imageapi.filedata.repository;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;



public class GoogleDriveRepository  {

	 public static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	 public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	 private static final String TOKENS_DIRECTORY_PATH = "tokens";

	 
	 private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	 private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	  
	 public static final String IMAGE_PATH_ID = "1-QBNuMNAdlvuflChf9sjGwm9_iqNlkFs";
	 public static final String IMAGE_PATH_WALLS_ID ="1w0xDioZ0f2mgSRWfTvt5LvQURNJaoO_w";
	 
	 
	  
	  public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
	        // Load client secrets.
	        InputStream in = GoogleDriveRepository.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
	        if (in == null) {
	            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
	        }
	        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

	        // Build flow and trigger user authorization request.
	        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	                .setAccessType("offline")
	                .build();
	        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
	        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	   }
	  public static Drive buildDriveService() throws GeneralSecurityException, IOException {
		  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	      return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	                .setApplicationName(APPLICATION_NAME)
	                .build();
	  }
	  
	  public static void main(String... args) throws IOException, GeneralSecurityException {
	      Drive service = buildDriveService();

	        // Print the names and IDs for up to 10 files.
	        FileList result = service.files().list()
	                .setPageSize(50)
	                .setFields("nextPageToken, files(id, name)")
	                .execute();
	        List<File> files = result.getFiles();
	        if (files == null || files.isEmpty()) {
	            System.out.println("No files found.");
	        } else {
	            for (File file : files) {
	                System.out.printf("%s (%s)\n", file.getName(), file.getId());
	            }
	        }
	    }
	    
	  
	  public static List<String> listAll() throws IOException, GeneralSecurityException {
	     
		  List<String> resultadoFinal = new ArrayList<String>();
		  
	      Drive service = buildDriveService();

	        // Print the names and IDs for up to 10 files.
	        FileList result = service.files().list()
	                .setPageSize(10)
	                .setFields("nextPageToken, files(id, name)")
	                .execute();
	        List<File> files = result.getFiles();
		        
		    if (files == null || files.isEmpty()) {
		          System.out.println("No files found.");
		    } else {
		        System.out.println("Files:");
		        for (com.google.api.services.drive.model.File file : files) {
		                resultadoFinal.add( file.getName());
		         }
		    }
		    return resultadoFinal;
	   }
	 

	  public static String retrieveFileById(String id) throws IOException, GeneralSecurityException {
		  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
				buildDriveService().files().get(id)
				    .setFields("id")
				    .executeMediaAndDownloadTo(outputStream);
				return "data:image/jpeg;base64," + Base64
				          .getEncoder()
				          .encodeToString(outputStream.toByteArray());
			
		}
	  public static ByteArrayOutputStream retrieveFileOutputStreamById(String id)throws IOException, GeneralSecurityException  {
		  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
				buildDriveService().files().get(id)
				    .setFields("id")
				    .executeMediaAndDownloadTo(outputStream);
				return outputStream;
			
		}
	


	
	public static String saveMapImageFile(java.io.File file, String fileName,String fileType,String drivePathId) throws IOException, GeneralSecurityException{
		File fileMetadata = new File();
		fileMetadata.setName(fileName);
		fileMetadata.setParents(Collections.singletonList(drivePathId));
		FileContent mediaContent = new FileContent(fileType, file);
		
		File googleFile = buildDriveService().files().create(fileMetadata, mediaContent)
			    .setFields("id")
			    .execute();
		return googleFile.getId();
	}
	/*
	public static String saveNumberArray(java.io.File file, String fileName) throws IOException, GeneralSecurityException{
		File fileMetadata = new File();
		fileMetadata.setName(fileName);
		fileMetadata.setParents(Collections.singletonList(IMAGE_PATH_ID));
		FileContent mediaContent = new FileContent("image/jpeg", file);
		
		File googleFile = buildDriveService().files().create(fileMetadata, mediaContent)
			    .setFields("id")
			    .execute();
		return googleFile.getId();
	}
	*/
	
	  
}
