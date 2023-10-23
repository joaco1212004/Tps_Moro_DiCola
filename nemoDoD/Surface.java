package nemoDoD;

public class Surface extends Depth{
    
    public void ascend(Nemo submarine){}
    
    public void descend(Nemo submarine){
    	submarine.position.z -= 1;
    	submarine.depths.add(new FirstLevelOfInmersion());
    }    

	public void ejectCapsule(Nemo submarine) {
		submarine.capsule = true;
	}
}