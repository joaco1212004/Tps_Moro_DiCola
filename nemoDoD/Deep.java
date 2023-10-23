package nemoDoD;

public class Deep extends Depth {
	
	public static final String MESSAGE_ERROR_CAPSULE = "The capsule cannot be launched below the first level of immersion";
    
    public void ascend(Nemo submarine){
    	submarine.position.z += 1;
    	submarine.depths.remove(submarine.depths.size() - 1);
    }
    public void descend(Nemo submarine){
    	submarine.position.z -= 1;
    	submarine.depths.add(new Deep());
    }

	public void ejectCapsule(Nemo submarine) {
		throw new RuntimeException(MESSAGE_ERROR_CAPSULE);
	}
}