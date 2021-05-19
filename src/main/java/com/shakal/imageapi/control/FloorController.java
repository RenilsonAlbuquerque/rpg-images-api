package com.shakal.imageapi.control;

import java.util.List;

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

import com.shakal.imageapi.contracts.service.IFloorMarkService;
import com.shakal.imageapi.contracts.service.IFloorService;
import com.shakal.imageapi.dto.create.FloorCreateDTO;
import com.shakal.imageapi.dto.info.FloorInfoDTO;
import com.shakal.imageapi.dto.info.MapWallsDTO;
import com.shakal.imageapi.dto.overview.FloorMarkOverviewDTO;
import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;




@CrossOrigin
@RestController
@RequestMapping("/floor")
public class FloorController {
	
	@Autowired
	private IFloorService floorService;
	
	@Autowired
	private IFloorMarkService floorMarkService;
	
	@GetMapping("/info/{id}")
	public ResponseEntity<FloorInfoDTO> getFloorInfoById(@PathVariable Long id) throws ResourceNotFoundException {
	    return new ResponseEntity<FloorInfoDTO>(this.floorService.getFloorById(id), HttpStatus.OK);
	}
	@PostMapping(value="/create/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createPlaces(@PathVariable Long id,@RequestBody List<FloorCreateDTO> inputDto) throws ResourceNotFoundException, FileManagementException{
        return new ResponseEntity<Boolean>(this.floorService.createFloor(id,inputDto), HttpStatus.OK);
    }
	
	@PostMapping(value="/update-walls-map/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateWallsMapFloor(@PathVariable Long id,@RequestBody List<MapWallsDTO> inputDto) throws ResourceNotFoundException, FileManagementException{
        return new ResponseEntity<Boolean>(this.floorService.updateFloorWallsImage(id,inputDto), HttpStatus.OK);
    }
	@GetMapping("/list/marks/{id}")
	public ResponseEntity<List<FloorMarkOverviewDTO>> getPlacesMarksListByPlaceId(@PathVariable Long id) throws ResourceNotFoundException {
	    return new ResponseEntity<List<FloorMarkOverviewDTO>>(this.floorMarkService.getAllPlaceMarksOfPlace(id), HttpStatus.OK);
	}

}
