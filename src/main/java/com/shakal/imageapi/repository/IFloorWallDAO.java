package com.shakal.imageapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shakal.imageapi.model.floor.FloorWall;


public interface IFloorWallDAO extends JpaRepository<FloorWall,Long> {
	
	@Modifying
	@Query("delete from FloorWall pw where pw.floor.id= ?1")
	void deletePlacesWallsByFloorId(long floorId);
	
}
