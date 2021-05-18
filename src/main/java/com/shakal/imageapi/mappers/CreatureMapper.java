package com.shakal.imageapi.mappers;

import com.shakal.imageapi.dto.create.CreatureTokenCreateDTO;
import com.shakal.imageapi.model.Creature;

public class CreatureMapper {
	
	public static Creature createDtoToEntity(CreatureTokenCreateDTO inputDto) {
		Creature entity = new Creature();
		entity.setId(inputDto.getId());
		entity.setImagePath(inputDto.getImagePath());
		return entity;
	}

}
