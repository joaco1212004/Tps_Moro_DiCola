package nemoDoD;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Messages {
	
	private char name;
	public static ArrayList<Messages> messageOptions = new ArrayList<>(Arrays.asList(new GoUp(), new GoDown(), new RotateLeft(), new RotateRight(), new MoveForward(), new OpenCapsuleGate()));
	
	public Messages(char name) {
		this.name = name;
	}

	public boolean isTheCorrectCommand(char charAt) {
		return charAt == name;
	}

	public void action(Nemo submarine) {}
	
	public static Messages iteration(char order) {
		return messageOptions.stream()
			    			 .filter(message -> message.isTheCorrectCommand(order))
			    			 .findFirst()
			    			 .orElse(null);
	}
}