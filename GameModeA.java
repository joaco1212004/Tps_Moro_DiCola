package Linea;

public class GameModeA extends GameMode {
    
    public GameModeA() {
        super('A');
    }
    
    public boolean winCondition (Linea dashoboard , String player){
        return dashoboard.verticalOrHorizontalWin(player);
    }
}