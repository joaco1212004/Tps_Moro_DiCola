package Linea;

import java.util.List;

public abstract class GameMode {
	
    char gameMode;

    public static List<GameMode> gameModes = List.of(new GameModeA(), new GameModeB(), new GameModeC());

    public GameMode(char gameMode) {
        this.gameMode = gameMode;
    }
    
    public abstract boolean winCondition(Linea board , String player);

    public  boolean canHandle(char mode){
        return this.gameMode == mode;
    };
    
    public static GameMode getReferee(char mode) {
        return gameModes.stream()
                	    .filter(referee -> referee.canHandle(mode))
                	    .findFirst()
                	    .orElseThrow(() -> new RuntimeException(Linea.nonExistingGameModeErrorMessage));
    }

    public char getType(){
        return this.gameMode;
    }
}
