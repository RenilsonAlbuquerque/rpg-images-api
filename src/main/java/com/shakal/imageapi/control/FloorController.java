package com.shakal.imageapi.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shakal.imageapi.contracts.service.IFloorService;
import com.shakal.imageapi.dto.FloorDetailDTO;
import com.shakal.imageapi.dto.GenericImageDTO;
import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/floor")
public class FloorController {
	
	@Autowired
	private IFloorService floorService;
	
	@PostMapping(value="/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> saveFloorImage(@RequestBody FloorDetailDTO inputDto) throws FileManagementException{
		return new ResponseEntity<Boolean>(this.floorService.saveFloor(inputDto), HttpStatus.OK);
    }
	
	@GetMapping(value="/{id}")
    public ResponseEntity<GenericImageDTO> getFloorImage(@PathVariable Long id) throws ResourceNotFoundException{
		return new ResponseEntity<GenericImageDTO>(this.floorService.getFloorImageToken(id), HttpStatus.OK);
    }

}
