package nemoDoD;

public class East extends CardinalPoint {

	public East() {
		super("East");
	}

	public void rotateLeft(Nemo submarine) {
		submarine.direction = new North();
	}
	
	public void rotateRight(Nemo submarine) {
		submarine.direction = new South();
	}
	
	public void moveForward(Nemo submarine) {
		submarine.position.x += 1;
	}
}