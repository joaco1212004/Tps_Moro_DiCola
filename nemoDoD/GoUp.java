package nemoDoD;

public class GoUp extends Messages {

	public GoUp() {
		super('u');
	}
	
	public void action(Nemo submarine) {
		submarine.depths.get(submarine.depths.size() - 1).ascend(submarine);
	}
}