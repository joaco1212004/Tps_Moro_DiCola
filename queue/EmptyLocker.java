package queue;

public class EmptyLocker extends Lockers {
	
	public static String QueueIsEmpty = "Queue is empty";
	
	public Object take() {
    	throw new Error(QueueIsEmpty); 
    }

    public Object head() {
    	throw new Error(QueueIsEmpty);
    }
    
    public Object GiveBack() {
    	throw new Error(QueueIsEmpty);
    }
}
	