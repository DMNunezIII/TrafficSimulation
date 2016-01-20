package exercise1;

import greenfoot.GreenfootImage;

public enum LightColor {
	GREEN(new GreenfootImage("images\\trafficLightGreen.png")),
	YELLOW(new GreenfootImage("images\\trafficLightYellow.png")),
	RED(new GreenfootImage("images\\trafficLightRed.png"));
	GreenfootImage color;
	private LightColor(GreenfootImage color){
		this.color = color;
	}
	public GreenfootImage getImage(){
		return color;
	}
}
