package Linea;

public class GameModeC extends GameMode {
	
    public GameModeC(){
       super('C');
    }
    public boolean winCondition (Linea board, String player){
        return board.verticalOrHorizontalWin(player) || board.diagonalWin(player);
    }
}