package nemoDoD;

public class North extends CardinalPoint {

	public North() {
		super("North");
	}

	public void rotateLeft(Nemo submarine) {
		submarine.direction = new West();
	}
	
	public void rotateRight(Nemo submarine) {
		submarine.direction = new East();
	}
	
	public void moveForward(Nemo submarine) {
		submarine.position.y += 1;
	}
}