package exercise1;

import greenfoot.Actor;

public class Car extends Actor implements IntersectionStates{
	private Direction direction;
	private String[] carImages = {"images\\topCarBlue.png", "images\\topCarPurple.png", "images\\topCarRed.png", "images\\topCarYellow.png"};
	public enum Speed{
		STOPPED,
		SLOW,
		FAST;
	}
	private int carSpeed = Speed.FAST.ordinal();
	public Car(Direction direction){
		this.direction = direction;
		setImage(carImages[TrafficWorld.rng.nextInt(4)]);
		setRotation(direction.getRotation());
	}
	public void act(){
		move(carSpeed);
		if(isAtEdge()){
			if(getRotation() == 0|| getRotation() == 180){
				if(getX()<TrafficWorld.WORLD_WIDTH/2){
					setLocation(TrafficWorld.WORLD_WIDTH, getY());
				}else {
					setLocation(0,getY());
				}
			}else {
				if(getY()<TrafficWorld.WORLD_HEIGHT/2){
					setLocation(getX(), TrafficWorld.WORLD_HEIGHT);
				}else {
					setLocation(getX(), 0);
				}
			}
		}
	}
	@Override
	public void approaching(LightColor color) {
		if(color==LightColor.YELLOW){
			carSpeed = Speed.SLOW.ordinal();
		}else if(color==LightColor.RED){
			carSpeed = Speed.STOPPED.ordinal();
		}else {
			carSpeed = Speed.FAST.ordinal();
		}
	}
	@Override
	public void entering(LightColor color) {
		carSpeed = Speed.FAST.ordinal();
	}
	@Override
	public void leaving() {
		carSpeed = Speed.FAST.ordinal();
	}
}
