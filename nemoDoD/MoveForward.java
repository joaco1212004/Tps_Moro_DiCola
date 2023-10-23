package nemoDoD;

public class MoveForward extends Messages {

	public MoveForward() {
		super('f');
	}

	public void action(Nemo submarine) {
		submarine.direction.moveForward(submarine);
	}
}