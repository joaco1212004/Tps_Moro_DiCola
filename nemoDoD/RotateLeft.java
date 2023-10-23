package nemoDoD;

public class RotateLeft extends Messages {

	public RotateLeft() {
		super('l');
	}
	
	public void action(Nemo submarine) {
		submarine.direction.rotateLeft(submarine);
	}
}