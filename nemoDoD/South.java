package nemoDoD;

public class South extends CardinalPoint {

	public South() {
		super("South");
	}

	public void rotateLeft(Nemo submarine) {
		submarine.direction = new East();
	}
	
	public void rotateRight(Nemo submarine) {
		submarine.direction = new West();
	}
	
	public void moveForward(Nemo submarine) {
		submarine.position.y -= 1;
	}
}