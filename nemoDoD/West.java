package nemoDoD;

public class West extends CardinalPoint {

	public West() {
		super("West");
	}

	public void rotateLeft(Nemo submarine) {
		submarine.direction = new South();
	}
	
	public void rotateRight(Nemo submarine) {
		submarine.direction = new North();
	}
	
	public void moveForward(Nemo submarine) {
		submarine.position.x -= 1;
	}
}