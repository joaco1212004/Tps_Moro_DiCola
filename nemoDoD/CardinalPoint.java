package nemoDoD;

public abstract class CardinalPoint {
	
	private String name;
	
	public CardinalPoint(String name) {
		this.name = name;
	}
	
	public String stringName() {
		return name;
	}

	public void rotateLeft(Nemo submarine) {}
	
	public void rotateRight(Nemo submarine) {}

	public void moveForward(Nemo submarine) {}
}