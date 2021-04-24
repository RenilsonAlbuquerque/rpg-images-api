package com.shakal.imageapi.model.enums;

public enum SocialTypeEnum {
	
	FACEBOOK(1),GOOGLE(2);
	
	private int value;
	
	private SocialTypeEnum (int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	public static SocialTypeEnum fromInteger(int value) {
	    switch(value) {
	    	case 1:
	    		return FACEBOOK;
	    	case 2:
	            return GOOGLE;
	    }
	    return null;
	 }

}
