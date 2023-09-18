package queue;

public class NotEmptyLocker extends Lockers {
	
	private Object name;

	public NotEmptyLocker(Object cargo) {
		name = cargo;
	}

    public Object take() {
    	return this;
    }

    public Object head() {
    	return this;
    }
    
    public Object GiveBack() {
    	return name;
    }
    
}
