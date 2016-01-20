package exercise1;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class TrafficLight extends Actor{
	private LightColor light;
	private Direction direction;
	public TrafficLight(LightColor light, Direction direction){
		this.light = light;
		this.direction = direction;
		turn(90+direction.getRotation());
	}
	public void setColor(LightColor color){
		light = color;
	}
	public GreenfootImage getImage(){
		return light.getImage();
	}
	public LightColor getColor(){
		return light;
	}
	public int getRotation(){
		return direction.getRotation();
	}
	public int getLightHeight(){
		return light.getImage().getHeight();
	}
	public int getLightWidth(){
		return light.getImage().getHeight();
	}
	public void act(){
		setImage(light.getImage());
	}
}