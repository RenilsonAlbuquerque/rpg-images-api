package com.shakal.imageapi.mappers;

import com.shakal.imageapi.dto.info.MapWallsDTO;
import com.shakal.imageapi.model.floor.Floor;
import com.shakal.imageapi.model.floor.FloorWall;

public class FloorWallsMapper {

	public static MapWallsDTO entityToDTO(FloorWall entity) {
		MapWallsDTO result = new MapWallsDTO();
		result.setStartPositionX(entity.getStartPositionX());
		result.setStartPositionY(entity.getStartPositionY());
		result.setEndPositionX(entity.getEndPositionX());
		result.setEndPositionY(entity.getEndPositionY());
		return result;
	}
	public static FloorWall dtoToEntity(MapWallsDTO entity,Floor floor) {
		FloorWall result = new FloorWall();
		result.setStartPositionX(entity.getStartPositionX());
		result.setStartPositionY(entity.getStartPositionY());
		result.setEndPositionX(entity.getEndPositionX());
		result.setEndPositionY(entity.getEndPositionY());
		result.setFloor(floor);
		return result;
	}
}
