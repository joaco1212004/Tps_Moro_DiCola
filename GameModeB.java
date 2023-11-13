package Linea;

public class GameModeB extends GameMode{
	
    public GameModeB(){
       super('B');
    }
    public boolean winCondition (Linea board, String player){
       return board.diagonalWin(player);
    }
}