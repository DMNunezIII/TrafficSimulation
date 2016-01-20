package exercise1;

import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Road extends Actor{
	private final static int ROAD_WIDTH = 50;
	public Road(int length, int angle){
		GreenfootImage road = new GreenfootImage(length, ROAD_WIDTH);
		setImage(road);
		road.setColor(Color.gray);
		road.fill();
		turn(angle);
	}
	public int getRoadWidth(){
		return ROAD_WIDTH;
	}
}
