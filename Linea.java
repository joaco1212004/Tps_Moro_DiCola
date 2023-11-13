package Linea;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {
    public static String Red = "\u001B[31m" + "O" + "\u001B[0m";
	public static String Blue = "\u001B[34m" + "X" + "\u001B[0m";
    public static String emptySlot = "--";
    
    public static String nonExistingGameModeErrorMessage = "Theres is no such game mode";
    public static String notRedsTurnErrorMessage = "Not Red's turn";
	public static String notBlueTurnErrorMessage = "Not blue's turn";
    public static String columnOutOfBoundsErrorMessage = "Column out of bounds";
    public static String thisSlotIsFullErrorMessage = "This slot is full";
    public static String cannotPlayWhenTheGameIsOverErrorMessage = "Cannot play when game is over";

    public int base;
    public int height;
    public GameMode gameMode;
    private GameState state;
    private ArrayList< ArrayList<String> > columns = new ArrayList< ArrayList<String> >();
    private Printer printer = new Printer(this);
    
    public Linea(int base, int height, char gameMode){
        this.base = base;
        this.height = height;
        this.gameMode =  GameMode.getReferee(Character.toUpperCase(gameMode));
        this.state= new PlayerRed(this);
        columns = IntStream.range(0, base).mapToObj(column -> new ArrayList<String>()).collect(Collectors.toCollection(ArrayList :: new));
    }
    
    public String show() {
        return printer.show();
    }
    
    public void playRedAt(int column) {
        checkItIsInBounds(column);
        state.playRedAt(column);
    }
    
    public void playBlueAt(int column) {
        checkItIsInBounds(column);
        state.playBlueAt(column);
    }

    public void addPieceAt(int column, String piece) {
        if ( columns.get(column-1).size() == height ) {
            throw new RuntimeException(thisSlotIsFullErrorMessage);
        }
        columns.get(column-1).add(piece);
    }

    public void checkItIsInBounds(int columnNumber) {
        if ( columnNumber < 1 || columnNumber > this.base ) {
            throw new RuntimeException(columnOutOfBoundsErrorMessage);
        }
    }	

    public boolean didRedWin() {
        return gameMode.winCondition(this, Red);
    }
    
    public boolean didBlueWin() {
        return gameMode.winCondition(this, Blue);
    }
    
    
    public boolean verticalOrHorizontalWin(String piece) {
        boolean verticalWin = IntStream.range(0, this.base)
                							.anyMatch(col -> IntStream.range(0, this.height - (4 - 1))
                									.anyMatch(row -> IntStream.range(0, 4)
                											.allMatch(k -> this.getPieceAt(col, row + k) == piece)));
        
        boolean horizontalWin = IntStream.range(0, this.base - (4 - 1))
                						 	.anyMatch(col -> IntStream.range(0, this.height)
                						 			.anyMatch(row -> IntStream.range(0, 4)
                						 					.allMatch(k -> this.getPieceAt(col + k, row) == piece)));
        	
        return verticalWin || horizontalWin;
    }
    
    public boolean diagonalWin(String piece) {
        boolean leftSlantDiagonal = IntStream.range(0, this.base - (4 - 1))
        									 	.anyMatch(col -> IntStream.range(0, this.height - (4 - 1))
        									 			.anyMatch(row -> IntStream.range(0, 4)
        									 					.allMatch(k -> this.getPieceAt(col + k, row + k) == piece)));
        
        boolean rightSlantDiagonal = IntStream.range(0, this.base - (4 - 1))
        										.anyMatch(col -> IntStream.range(0, this.height - (4 - 1))
        												.anyMatch(row -> IntStream.range(0, 4)
        														.allMatch(k -> this.getPieceAt(this.base - 1 - col - k, row + k) == piece)));
        
        return rightSlantDiagonal || leftSlantDiagonal;
    }
    
    public void switchState (GameState newState){
        state = newState;
    }
    
    public boolean ended() {
        return state.gameEnded();
    }
    
    public boolean isPlayingRed() {
        return state.isPlayingRed();
    }
    
    public boolean isPlayingBlue() {
        return state.isPlayingBlue();
    }
    
    public boolean winnerIsRed() {
        return state.redWon();
    }
    
    public boolean winnerIsBlue() {
        return state.blueWon();
    }
    
    public boolean fullBoard(){
        return this.getAmountOfPiecesDisplayed() == this.base * this.height;
    }
    
    public boolean draw() {
        return state.draw() ;
    }

    public GameState getCurrentGameState() {
        return this.state;
    }

    public char getCurrentGameMode() {
        return gameMode.getType();
    }

    public int getAmountOfPiecesDisplayed() {
        return this.columns.stream().mapToInt(column -> column.size()).sum();
    }
    
    public String getPieceAt(int column, int row) {
        return columns.get(column).stream().skip(row).findFirst().map(String::valueOf).orElse(emptySlot);
    }

}
