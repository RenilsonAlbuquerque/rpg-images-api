package com.shakal.imageapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakal.imageapi.model.floor.FloorImage;

public interface IFloorImageDAO extends JpaRepository<FloorImage,Long>{

}
