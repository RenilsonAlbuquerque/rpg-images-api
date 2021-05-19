package com.shakal.imageapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shakal.imageapi.model.floor.Floor;


public interface IFloorDAO extends JpaRepository<Floor,Long>{

	@Query("SELECT fl FROM Floor fl where fl.placeId = ?1")
	List<Floor> getFloorsByPlaceId(long floorId);
}
