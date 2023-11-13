package Linea;

public class BlueWins  extends GameState{
	
    public BlueWins(Linea currentGame) {
     super(currentGame);
    }
    
    public String show() {
    	return "The winner is: Blue";
    }

    public boolean gameEnded() {
    	return true;
    }
 
    public boolean blueWon() {
    	return true;
    }
    
    public boolean validMovement() {
    	return currentGame.didBlueWin();
    }
    
    public void playRedAt(int column) {
    	throw new RuntimeException(Linea.cannotPlayWhenTheGameIsOverErrorMessage);
    }

    public void playBlueAt(int column) {
    	throw new RuntimeException(Linea.cannotPlayWhenTheGameIsOverErrorMessage);
    }
}