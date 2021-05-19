package com.shakal.imageapi.dto.create;

public class FloorCreateDTO {
	
	private long placeId;
	private String map;
	private double xDimension;
	private double yDimension;
	private double squareDimension;
	private int squareDimensionCm;
	private Long floorOrder;
	
	

	public long getPlaceId() {
		return placeId;
	}
	public void setPlaceId(long placeId) {
		this.placeId = placeId;
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
	public int getSquareDimensionCm() {
		return squareDimensionCm;
	}
	public void setSquareDimensionCm(int squareDimensionCm) {
		this.squareDimensionCm = squareDimensionCm;
	}
	public Long getFloorOrder() {
		return floorOrder;
	}
	public void setFloorOrder(Long floorOrder) {
		this.floorOrder = floorOrder;
	}

	
	
}
