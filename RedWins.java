package Linea;

public class RedWins extends GameState {
	
    public RedWins(Linea currentGame) {
        super(currentGame);
    }
    
    public String show() {
        return "The winner is: Red";
    }

    public boolean gameEnded() {
        return true;
    }
    
    public boolean redWon() {
        return true;
    }
    
    public boolean validMovement() {
        return currentGame.didRedWin();
    }
    
    public void playRedAt(int column) {
        throw new RuntimeException(Linea.cannotPlayWhenTheGameIsOverErrorMessage);
    }

    public void playBlueAt(int column) {
        throw new RuntimeException(Linea.cannotPlayWhenTheGameIsOverErrorMessage);
    }

}