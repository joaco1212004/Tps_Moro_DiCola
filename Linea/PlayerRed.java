package Linea;

public class PlayerRed  extends GameState{
	
    public PlayerRed(Linea currentGame) {
        super(currentGame);
    }
    
    public String show() {
        return "Red's turn";
    }
    
    public boolean isPlayingRed() {   
        return true;
    }
    
    public void playRedAt(int column) {
        currentGame.addPieceAt(column , Linea.Red);
        currentGame.switchState(selecGameState());
    }

    public boolean validMovement() {
        return !currentGame.fullBoard() && !currentGame.didRedWin() && !currentGame.didBlueWin() && !currentGame.isPlayingRed() && currentGame.isPlayingBlue();
    }
    
    public void playBlueAt(int column) {
        throw new RuntimeException(Linea.notBlueTurnErrorMessage);
    }
}