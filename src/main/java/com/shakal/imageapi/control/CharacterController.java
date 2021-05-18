package com.shakal.imageapi.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shakal.imageapi.contracts.service.ICreatureService;
import com.shakal.imageapi.exception.FileManagementException;
import com.shakal.imageapi.exception.ResourceNotFoundException;
import com.shakal.imageapi.dto.CreatureTokenDTO;
import com.shakal.imageapi.dto.GenericImageDTO;
import com.shakal.imageapi.dto.create.CreatureTokenCreateDTO;

@CrossOrigin
@RestController
@RequestMapping("/creature")
public class CharacterController {
	
	@Autowired
	private ICreatureService creatureService;
	
	@GetMapping(value="/token/{id}")
    public ResponseEntity<CreatureTokenDTO> getCharacterSheet(@PathVariable Long id) throws ResourceNotFoundException{
		CreatureTokenDTO  result = this.creatureService.getCreatureToken(id);
		return new ResponseEntity<CreatureTokenDTO>(result, HttpStatus.OK);
    }
	@PostMapping(value="/token")
    public ResponseEntity<Boolean> saveCreatureToken(@RequestBody GenericImageDTO inputDto) throws FileManagementException{
		return new ResponseEntity<Boolean>(this.creatureService.saveCreatureToken(inputDto),HttpStatus.OK);
    }

}
