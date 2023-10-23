package nemoDoD;

public class OpenCapsuleGate extends Messages {

	public OpenCapsuleGate() {
		super('m');
	}
	
	public void action(Nemo submarine) {
		submarine.depths.get(submarine.depths.size() - 1).ejectCapsule(submarine);
	}
}