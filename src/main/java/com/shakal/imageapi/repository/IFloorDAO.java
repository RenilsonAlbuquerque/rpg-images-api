package com.shakal.imageapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakal.imageapi.model.floor.Floor;


public interface IFloorDAO extends JpaRepository<Floor,Long>{

}
