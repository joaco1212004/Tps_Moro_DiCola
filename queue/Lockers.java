package queue;

public abstract class Lockers {
	public abstract Object take();
	public abstract Object head();
	public abstract Object GiveBack();
}

