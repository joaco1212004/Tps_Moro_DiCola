package nemoDoD;

public class FirstLevelOfInmersion extends Depth {
	
    public void ascend(Nemo submarine){
    	submarine.position.z += 1;
    	submarine.depths.add(new Surface());
    }
    
    public void descend(Nemo submarine){
    	submarine.position.z -=1;
    	submarine.depths.add(new Deep());
    }

	public void ejectCapsule(Nemo submarine) {
		submarine.capsule = true;
	}
}