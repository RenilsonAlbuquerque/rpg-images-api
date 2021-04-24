package com.shakal.imageapi.filedata.strategy;

import java.io.File;

import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.filedata.enums.PathEnum;

public interface IExternalFileStorageStrategy {

	/*** Given the id of implementation, returns a base64 of File 
	 * @return base64 file
	**/ 
	String retrieveFileById(String id,String filePath) throws ResourceNotFoundException;
	
	/*** Given an string containing a base64 file to save and it's name, returns the identifier 
	 * @return identifier of saved file 
	 **/
	String saveFile(File file,String fileName,String filePath) throws FileManagementException;
	
	/*** Given the id of api, returns a ByteArrayOutputStream of File
	 *  @return base64 of /10 scaled file
	 */
	String retrieveMinimap(String fileId,String filePath) throws ResourceNotFoundException;
	
	
	String getPath(PathEnum pathSelector);
}
