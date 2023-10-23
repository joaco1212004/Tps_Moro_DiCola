package nemoDoD;

public class RotateRight extends Messages {

	public RotateRight() {
		super('r');
	}

	public void action(Nemo submarine) {
		submarine.direction.rotateRight(submarine);
	}
}