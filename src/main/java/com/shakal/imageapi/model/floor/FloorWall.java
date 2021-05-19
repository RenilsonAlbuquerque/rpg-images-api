package com.shakal.imageapi.model.floor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "tb_floor_wall")
public class FloorWall {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int startPositionX;
	private int startPositionY;
	private int endPositionX;
	private int endPositionY;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Floor.class)
	@JoinColumn(name ="floor_id", referencedColumnName = "id")
	private Floor floor;
	
	public int getStartPositionX() {
		return startPositionX;
	}
	public void setStartPositionX(int startPositionX) {
		this.startPositionX = startPositionX;
	}
	public int getStartPositionY() {
		return startPositionY;
	}
	public void setStartPositionY(int startPositionY) {
		this.startPositionY = startPositionY;
	}
	public int getEndPositionX() {
		return endPositionX;
	}
	public void setEndPositionX(int endPositionX) {
		this.endPositionX = endPositionX;
	}
	public int getEndPositionY() {
		return endPositionY;
	}
	public void setEndPositionY(int endPositionY) {
		this.endPositionY = endPositionY;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Floor getFloor() {
		return floor;
	}
	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	
	

}
