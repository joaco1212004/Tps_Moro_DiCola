package Linea;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState {
	
    protected Linea currentGame;
    protected ArrayList<GameState> State;

    public GameState(Linea currentGame) {
        this.currentGame = currentGame;
    }
    
    public abstract String show();
    public abstract void playRedAt(int column);
    public abstract void playBlueAt(int column);
    public abstract boolean validMovement();
    
    public boolean isPlayingRed() {
        return false;
    }

    public boolean isPlayingBlue() {
        return false;
    }
    
    public GameState selecGameState (){
        ArrayList<GameState> gameStates = new ArrayList<GameState>(List.of(
                					  new PlayerRed(currentGame), new PlayerBlue(currentGame), new RedWins(currentGame), new BlueWins(currentGame), new GameDraw(currentGame)
                					  ));
                
        return gameStates.stream().filter(each -> each.validMovement()).findFirst().get();
    }

    public boolean redWon(){
        return false;
    }
    
    public boolean blueWon(){
        return false;
    }
    
    public boolean draw(){
        return false;
    }
    
    public boolean gameEnded(){
        return false;
    }
}