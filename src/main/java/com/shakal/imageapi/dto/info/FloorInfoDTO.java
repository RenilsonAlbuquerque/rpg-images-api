package com.shakal.imageapi.dto.info;

import java.util.List;



public class FloorInfoDTO {

	private long id;
	
	private String map;
	
	private List<MapWallsDTO> walls;
	
	private double xDimension;
	
	private double yDimension;
	
	private double squareDimension;
	
	private double naturalHeight;
	
	private double naturalWidth;

	private int squareSizeCm;
	
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

	public double getxDimension() {
		return xDimension;
	}

	public void setxDimension(double xDimension) {
		this.xDimension = xDimension;
	}

	public double getyDimension() {
		return yDimension;
	}

	public void setyDimension(double yDimension) {
		this.yDimension = yDimension;
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

	public List<MapWallsDTO> getWalls() {
		return walls;
	}

	public void setWalls(List<MapWallsDTO> walls) {
		this.walls = walls;
	}

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

	

	
}
