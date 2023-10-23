package nemoDoD;

import java.util.ArrayList;
import java.util.Arrays;

public class Nemo {

	public Point position;
	public CardinalPoint direction;
	public boolean capsule;
	ArrayList<Depth> depths =  new ArrayList<>(Arrays.asList(new Surface()));

	public Nemo(CardinalPoint direction, Point position) {
		this.direction = direction;
		this.capsule = false;
		this.position = position;
	}

	public void move(String order) {
		order.chars().forEach( c -> move((char) c) );
	}
	
	public void move(char order) {
		Messages.iteration(order).action(this);
	}
	
	public int getX() {
		return position.x;
	}

	public int getY() {
		return position.y;
	}

	public int getZ() {
		return position.z;
	}
	
	public String getDirection() {
		return this.direction.stringName();
	}
}