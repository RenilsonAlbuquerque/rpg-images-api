package com.shakal.imageapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakal.imageapi.model.floor.FloorImage;

public interface IFloorDAO extends JpaRepository<FloorImage,Long>{

}
