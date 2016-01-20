package exercise1;

public enum Direction {
	NORTH(270),
	SOUTH(90),
	EAST(0),
	WEST(180);
	int rotation;
	private Direction(int rotation){
		this.rotation = rotation;
	}
	public int getRotation(){
		return rotation;
	}
}
