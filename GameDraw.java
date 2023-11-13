package Linea;

public class GameDraw extends GameState {
	
    public GameDraw(Linea currentGame) {
        super(currentGame);
    }
    
    public String show() {
        return "Finished: Result: Draw";
    }
    
    public boolean gameEnded() {
        return true;
    }

    public boolean draw() {
        return true;
    }

    public boolean validMovement() {
        return currentGame.fullBoard() && !currentGame.didRedWin() && !currentGame.didBlueWin();
    }
    
    public void playRedAt(int column) {
        throw new RuntimeException(Linea.cannotPlayWhenTheGameIsOverErrorMessage);
    }

    public void playBlueAt(int column) {
        throw new RuntimeException(Linea.cannotPlayWhenTheGameIsOverErrorMessage);
    }
}