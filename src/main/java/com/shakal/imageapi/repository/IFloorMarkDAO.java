package com.shakal.imageapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakal.imageapi.model.floor.FloorMark;



public interface IFloorMarkDAO extends JpaRepository<FloorMark,Long>{

}
