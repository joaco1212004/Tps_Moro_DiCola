package Linea;

public class PlayerBlue extends GameState {

    public PlayerBlue(Linea currentGame) {
        super(currentGame);
    }
    
    public String show() {
        return "Blue's turn";
    }
    
    public boolean isPlayingBlue() {
        return true;
    }

    public void playBlueAt(int column) {
        currentGame.addPieceAt(column , Linea.Blue);
        currentGame.switchState(selecGameState());
    }

    public boolean validMovement() {
        return !currentGame.fullBoard() && !currentGame.didRedWin() && !currentGame.didBlueWin() && currentGame.isPlayingRed() && !currentGame.isPlayingBlue();
    }
    
    public void playRedAt(int column) {
        throw new RuntimeException(Linea.notRedsTurnErrorMessage);
    }
}