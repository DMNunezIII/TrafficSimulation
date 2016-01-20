package exercise1;

import java.awt.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import java.util.Random;

public class TrafficWorld extends World {
	public final static int WORLD_WIDTH = 1000;
	public final static int WORLD_HEIGHT = 750;
	private final static int CELL_SIZE = 1;
	private final static int X_HORIZONTAL_ROADS = 5;
	private final static int X_VERTICAL_ROADS = 7;
	private static int num = 0;
	private Car[] horizontalCars = new Car[X_HORIZONTAL_ROADS*2];
	private Car[] verticalCars = new Car[X_VERTICAL_ROADS*2];
	private Road[] horizontalRoad = new Road[X_HORIZONTAL_ROADS];
	private Road[] verticalRoad = new Road[X_VERTICAL_ROADS];
	private Intersection[] intersections = new Intersection[X_HORIZONTAL_ROADS*X_VERTICAL_ROADS];
	public static Random rng = new Random();
	public TrafficWorld(){
		super(WORLD_WIDTH,WORLD_HEIGHT,CELL_SIZE);
		GreenfootImage background = getBackground();
		background.setColor(Color.green);
		background.fill();
		for(int x = 0; x < intersections.length; x++){
			intersections[x] = new Intersection();
		}
		fillCarArray(horizontalCars, Direction.WEST, Direction.EAST);
		fillCarArray(verticalCars, Direction.SOUTH, Direction.NORTH);
		fillRoadArray(horizontalRoad, WORLD_WIDTH, 0);
		fillRoadArray(verticalRoad, WORLD_HEIGHT, Direction.SOUTH.getRotation());
		int roadHeight = horizontalRoad[0].getImage().getHeight();
		int carHeight = horizontalCars[0].getImage().getHeight();
		int hGapSize = (WORLD_HEIGHT-(roadHeight*horizontalRoad.length))/(horizontalRoad.length-1);
		int vGapSize = (WORLD_WIDTH-(roadHeight*verticalRoad.length))/(verticalRoad.length-1);
		drawRoads(horizontalRoad, "horizontal", hGapSize, roadHeight);
		drawRoads(verticalRoad, "vertical", vGapSize, roadHeight);
		drawCars(horizontalCars, "horizontal", hGapSize, carHeight, roadHeight);
		drawCars(verticalCars, "vertical", vGapSize, carHeight, roadHeight);
		drawIntersections(intersections, hGapSize, vGapSize, roadHeight);
	}
	public void fillCarArray(Car[] cars, Direction first, Direction second){
		for(int x = 0; x < cars.length; x++){
			cars[x++] = new Car(first);
			cars[x] = new Car(second);
		}
	}
	public void fillRoadArray(Road[] roads, int length, int angle){
		for(int i = 0; i < roads.length; i++){
			roads[i] = new Road(length, angle);
		}
	}
	public void drawRoads(Road[] roads, String orientation, int gap, int width){
		if(orientation.equals("horizontal")){
			for(int x = 0; x < roads.length; x++){
				addObject(roads[x], WORLD_WIDTH/2, ((width+gap)*x)+(width/2));
			}
		}else if(orientation.equals("vertical")){
			for(int x = 0; x < roads.length; x++){
				addObject(roads[x], ((width+gap)*x)+(width/2), WORLD_HEIGHT/2);
			}
		}
	}
	public void drawCars(Car[] cars, String orientation, int gap, int width, int roadHeight){
		if(orientation.equals("horizontal")){
			for(int x = 0; x < cars.length; x+=2){
				addObject(cars[x], rng.nextInt(WORLD_WIDTH), ((roadHeight+gap)*num)+(width/2));
				addObject(cars[x+1], rng.nextInt(WORLD_WIDTH), ((roadHeight+gap)*num)+(width/2)+width);
				num++;
			}
		}else if(orientation.equals("vertical")){
			for(int x = 0; x < cars.length; x+=2){
				addObject(cars[x], ((roadHeight+gap)*num)+(width/2), rng.nextInt(WORLD_HEIGHT));
				addObject(cars[x+1], ((roadHeight+gap)*num)+(width/2)+width, rng.nextInt(WORLD_HEIGHT));
				num++;
			}
		}
		num = 0;
	}
	public void drawIntersections(Intersection[] intersections, int gap1, int gap2, int value){
		int count = 0;
		for(int x = 0; x < X_VERTICAL_ROADS; x++){
			for(int y = 0; y < X_HORIZONTAL_ROADS; y++){
				addObject(intersections[count], x*(gap2+value)+(value/2), y*(gap1+value)+(value/2));
				intersections[count++].drawLights();
			}
		}
	}
	public void act(){
		for(Intersection i: intersections){
			for(Car c: horizontalCars){
				i.giveState(c, i.getHorizontalColor());
			}
			for(Car c: verticalCars){
				i.giveState(c, i.getVerticalColor());
			}
		}
	}
}
