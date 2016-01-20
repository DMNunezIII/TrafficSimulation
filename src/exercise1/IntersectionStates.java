package exercise1;

public interface IntersectionStates {
	public void approaching(LightColor color);
	public void entering(LightColor color);
	public void leaving();
}
