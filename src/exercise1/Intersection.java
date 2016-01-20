package exercise1;

import java.util.ArrayList;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Intersection extends Actor{
	private final static int INTERSECTION_SIZE = 50;
	private final static int INTERSECTION_HALF = INTERSECTION_SIZE/2;
	private final static int INTERSECTION_QUARTER = INTERSECTION_SIZE/4;
	private final static int TIMER_MAX = 250;
	private TrafficLight northLight;
	private TrafficLight southLight;
	private TrafficLight eastLight;
	private TrafficLight westLight;
	private GreenfootImage intersection = new GreenfootImage(INTERSECTION_SIZE, INTERSECTION_SIZE);
	private int lightTimer = TIMER_MAX;
	private LightColor verticalColor;
	private LightColor horizontalColor;
	private ArrayList<IntersectionStates> nearIntersection = new ArrayList<IntersectionStates>();
	private ArrayList<IntersectionStates> inIntersection = new ArrayList<IntersectionStates>();
	private ArrayList<IntersectionStates> wasInIntersection = new ArrayList<IntersectionStates>();
	public Intersection(){
		setImage(intersection);
	}
	public void act(){
		lightTimer--;
		lightState(northLight);
		lightState(southLight);
		lightState(eastLight);
		lightState(westLight);
		verticalColor = northLight.getColor();
		horizontalColor = eastLight.getColor();
		if(lightTimer<=0)
			lightTimer = TIMER_MAX;
		nearIntersection.addAll(getObjectsInRange(INTERSECTION_SIZE, IntersectionStates.class));
		inIntersection.addAll(getIntersectingObjects(IntersectionStates.class));
		for(int x = 0; x < inIntersection.size()-1; x++){
			IntersectionStates test = inIntersection.get(x);
			if(!getIntersectingObjects(IntersectionStates.class).contains(test)){
				do{
					inIntersection.remove(test);
				}while(inIntersection.contains(test));
				wasInIntersection.add(test);
			}
		}
		for(int x = 0; x < nearIntersection.size()-1; x++){
			IntersectionStates test = nearIntersection.get(x);
			if(!getObjectsInRange(INTERSECTION_SIZE, IntersectionStates.class).contains(test)){
				do{
					nearIntersection.remove(test);
				}while(nearIntersection.contains(test));
				wasInIntersection.remove(test);
			}
		}
	}
	public void lightState(TrafficLight light){
		switch(light.getColor()){
		case GREEN:
			if(lightTimer<=100){
				light.setColor(LightColor.YELLOW);
			}
			break;
		case YELLOW:
			if(lightTimer<=0){
				light.setColor(LightColor.RED);
			}
			break;
		case RED:
			if(lightTimer<=0){
				light.setColor(LightColor.GREEN);
			}
		}
	}
	public void drawLights(){
		verticalColor = LightColor.GREEN;
		horizontalColor = LightColor.RED;
		northLight = new TrafficLight(verticalColor, Direction.NORTH);
		southLight = new TrafficLight(verticalColor, Direction.SOUTH);
		eastLight = new TrafficLight(horizontalColor, Direction.EAST);
		westLight = new TrafficLight(horizontalColor, Direction.WEST);
		getWorld().addObject(northLight, this.getX(), this.getY()-(INTERSECTION_SIZE/2)-(northLight.getLightHeight()/2));
		getWorld().addObject(southLight, this.getX(), this.getY()+(INTERSECTION_SIZE/2)+(southLight.getLightHeight()/2));
		getWorld().addObject(eastLight, this.getX()+(INTERSECTION_SIZE/2)+(eastLight.getLightHeight()/2), this.getY());
		getWorld().addObject(westLight, this.getX()-(INTERSECTION_SIZE/2)-(westLight.getLightHeight()/2), this.getY());
	}
	public void giveState(IntersectionStates car, LightColor color){
		if(nearIntersection.contains(car)){
			if(inIntersection.contains(car)){
				car.entering(color);
			}else if(wasInIntersection.contains(car)){
				car.leaving();
			}else {
				car.approaching(color);
			}
		}
	}
	public LightColor getVerticalColor(){
		return verticalColor;
	}
	public LightColor getHorizontalColor(){
		return horizontalColor;
	}
}