package com.shakal.imageapi.model.floor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "tb_floor_image")
public class FloorImage {

	@Id
	private Long id;
	
	@Column(length = 500)
	private String imagePath;
	
	private int squareSize;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getSquareSize() {
		return squareSize;
	}

	public void setSquareSize(int squareSize) {
		this.squareSize = squareSize;
	}
	
	
}
