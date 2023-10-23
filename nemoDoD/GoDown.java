package nemoDoD;

public class GoDown extends Messages {

	public GoDown() {
		super('d');
	}
	
	public void action(Nemo submarine) {
		submarine.depths.get(submarine.depths.size() - 1).descend(submarine);
	}
}