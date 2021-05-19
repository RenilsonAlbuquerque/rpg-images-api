package com.shakal.imageapi.model.floor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.shakal.imageapi.model.base.BaseEntity;



@Entity
@Table(name= "tb_floor")
@Where(clause="deleted=0")
public class Floor extends BaseEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Long floorOrder;
	
	private String map;
	
	private double squareDimension;
	
	private double naturalHeight;
	
	private double naturalWidth;
	
	private int squareSizeCm;
	
	
	@OneToMany(mappedBy = "floor",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY, targetEntity = FloorMark.class)
	private List<FloorMark> marks;

	@OneToMany(mappedBy = "floor",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY, targetEntity = FloorWall.class)
	private List<FloorWall> walls;
	
	
	private long placeId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public double getSquareDimension() {
		return squareDimension;
	}

	public void setSquareDimension(double squareDimension) {
		this.squareDimension = squareDimension;
	}

	public double getNaturalHeight() {
		return naturalHeight;
	}

	public void setNaturalHeight(double naturalHeight) {
		this.naturalHeight = naturalHeight;
	}

	public double getNaturalWidth() {
		return naturalWidth;
	}

	public void setNaturalWidth(double naturalWidth) {
		this.naturalWidth = naturalWidth;
	}

	public int getSquareSizeCm() {
		return squareSizeCm;
	}

	public void setSquareSizeCm(int squareSizeCm) {
		this.squareSizeCm = squareSizeCm;
	}

	public List<FloorMark> getMarks() {
		return marks;
	}

	public void setMarks(List<FloorMark> marks) {
		this.marks = marks;
	}

	public List<FloorWall> getWalls() {
		return walls;
	}

	public void setWalls(List<FloorWall> walls) {
		this.walls = walls;
	}


	public Long getFloorOrder() {
		return floorOrder;
	}

	public void setFloorOrder(Long floorOrder) {
		this.floorOrder = floorOrder;
	}

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

	

	
	
	
}
