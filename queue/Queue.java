package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	
	private ArrayList<Lockers> components = new ArrayList<>(List.of(new EmptyLocker()));
    
    public boolean isEmpty() {
        return components.size() == 1;
    }

    public Queue add(Object cargo) {
    	components.add( 1, new NotEmptyLocker(cargo));
    	return this;
    }

    public Object take() {
    	Object LastComponent = components.get(components.size() - 1);
		components.remove(components.size() - 1);
    	return ((Lockers) LastComponent).GiveBack();
    }

    public Object head() {
    	Object LastComponent = components.get(components.size()- 1);
    	return ((Lockers) LastComponent).GiveBack();
    }
   
    public int size() {
    	return (components.size() - 1);
    }
} 