package com.shakal.imageapi.dto.commons;

public class NumberNumberDTO {

	private long key;
	private long value;
	
	public NumberNumberDTO() {
		
	}
	public NumberNumberDTO(long key,long value) {
		this.key = key;
		this.value = value;
	}
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	
	
}
