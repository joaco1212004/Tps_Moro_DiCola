package Linea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;

public class GameTest {

    private Linea game;
    private String R;
    private String B;
    
    @BeforeEach public void setUp() {
        game = new Linea(4, 4, 'A');
        R = Linea.Red;
        B = Linea.Blue;
    }
    
    @Test public void test00CheckIfTheGameInitializesCorrectly() {
    	checkHowTheGameIsDeveloping(4, 4, 'A', false, false, true, 0);
    }
    
    @Test public void test01CheckOfGameInitializesWithDifferentCharacteristics () {
        game = new Linea(7, 7, 'B');
        checkHowTheGameIsDeveloping(7, 7, 'B', false, false, true, 0);
    }
    
    @Test public void test02CheckIfBothPlayerCanDisplayATokens() {
    	playingTester(1, 1);
        checkHowTheGameIsDeveloping(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void test03CCheckIfBothPlayersRespectTheirTurns() {
        assertThrowsLike(() -> game.playBlueAt(1), Linea.notBlueTurnErrorMessage);
        game.playRedAt(1);
        assertThrowsLike(() -> game.playRedAt(1), Linea.notRedsTurnErrorMessage);
    }

    @Test public void test04NeitherRedOrBlueCanPlayOutOfBoundaries() {
        assertThrowsLike(() -> playingTester(5), Linea.columnOutOfBoundsErrorMessage);
        assertThrowsLike(() -> playingTester(1, 5), Linea.columnOutOfBoundsErrorMessage);
    }
    
    @Test public void test05CantPlayGameWithWrongGameMode() {
        assertThrowsLike(() -> new Linea(4, 4, 'D'), Linea.nonExistingGameModeErrorMessage);
    }
    
    @Test public void test06CantPlaceOverAfullcolumn() {
        assertThrowsLike(() -> playingTester(1, 1, 1, 1, 1), Linea.thisSlotIsFullErrorMessage);
    }
    
    @Test public void test07RedCanWinVertical() {
    	playingTester(4, 2, 4, 1, 4, 3, 4);
    	checkIfGameOver(true, true, false, false);
    }
    
    @Test public void test08BlueCanWinVertical() {
    	playingTester(2, 4, 1, 4, 3, 4, 1, 4);
    	checkIfGameOver(true, false, true, false);
    }
    
    @Test public void test09RedCanWinHorizontally() {
    	playingTester(1, 1, 2, 1, 3, 1, 4);
    	checkIfGameOver(true, true, false, false);
    }
    
    @Test public void test10BlueCanWinHorizontally() {
    	playingTester(1, 1, 2, 2, 3, 3, 1, 4, 1, 4);
    	checkIfGameOver(true, false, true, false);
    }
    
    @Test public void test11CantWinWithADiagonalInTheWrongGameMode() {
    	playingTester(1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4);
    	checkIfGameOver(false, false, false, false);
    }
    
    @Test public void test12CheckIfRedInGameModeB() {
        game = new Linea(4, 4, 'B');
        playingTester(1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4);
        checkIfGameOver(true, true, false, false);
    }
    
    @Test public void test13CheckIfBlueCanWinInGameModeB() {
        game = new Linea(4, 4, 'B');
        playingTester(2,4,1,1,3,3,2,2,1,1);
        checkIfGameOver(true, false, true, false);
    }

    @Test public void test14MakingSureYouCantWinHorizontallyInGameModeB() {
        game = new Linea(4, 4, 'B');
        playingTester(1, 1, 2, 2, 3, 3, 4, 4);
        checkIfGameOver(false, false, false, false);
    }
    
    @Test public void test15MakingSureYouCantWinVerticallyInGameModeB() {
        game = new Linea(4, 4, 'B');
        playingTester(1, 2, 1, 2, 1, 2, 1, 2);
        checkIfGameOver(false, false, false, false);
    }
    
    @Test public void test16CanWinVericallyGameModeC() {
        game = new Linea(4, 4, 'C');
        playingTester(1, 2, 1, 2, 1, 2, 1);
        checkIfGameOver(true, true, false, false);
    }
    
    @Test public void test17CanWinHorizontallyGameModeC() {
        game = new Linea(4, 4, 'C');
        playingTester(1, 1, 2, 2, 3, 3, 1, 4, 2, 4);
        checkIfGameOver(true, false, true, false);
    }
    
    @Test public void test18CanWinWithIncreasingDiagonalGameModeC() {
        game = new Linea(4, 4, 'C');
        playingTester(1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4);
        checkIfGameOver(true, true, false, false);
    }

    @Test public void test19CanWinWithDecreasingDiagonalGameModeC() {
        game = new Linea(4, 4, 'C');
        playingTester(2, 4, 1, 1, 3, 3, 2, 2, 1, 1);
        checkIfGameOver(true, false, true, false);
    }
    
    @Test public void test20CanDrawInGameModeC() {
        game = new Linea(4, 4, 'C');
        playingTester(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 2, 1, 4, 3);
        checkIfGameOver(true, false, false, true);
    }
    
    @Test public void test21BlueCantPlayAfterRedWon() {
    	playingTester(1, 1, 2, 2, 3, 3, 4);
        assertThrowsLike(() -> game.playBlueAt(1), Linea.cannotPlayWhenTheGameIsOverErrorMessage);
        checkIfGameOver(true, true, false, false);
    }
    
    @Test public void test22RedCantPlayAfterBlueWon() {
    	playingTester(1, 2, 1, 2, 1, 2, 4, 2);
        assertThrowsLike(() -> game.playRedAt(1), Linea.cannotPlayWhenTheGameIsOverErrorMessage);
        checkIfGameOver(true, false, true, false);
    }
    
    @Test public void test23CheckForADrawInTheDefaultGameMode() {
    	playingTester(1,1,1,1,2,3,2,3,2,3,3,2,4,4,4,4);
    	checkIfGameOver(true, false, false, true);
    }

    @Test public void test24CheckIfThePrinterCanShowTheEmptyBoard() {
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "State: Red's turn" + "\n";
        assertEquals(printGame, game.show());
    }
    
    @Test public void test25CheckHowTheMovementsAreShownToTheUser() {
    	playingTester(4);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│"+R +"│" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "State: Blue's turn" + "\n";
        assertEquals(printGame, game.show());
    }
    
    @Test public void test26PrinterAfterWinnigRED() {
    	playingTester(1, 1, 2, 2, 3, 3, 4);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│"+B+"│"+B+"│"+B+"│--│" + "\n" +
                            "│"+R+"│"+R+"│"+R+"│"+R+"│" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "State: The winner is: Red" + "\n";
        assertEquals(printGame, game.show());
    }
    
    private void assertThrowsLike(Executable executable, String message) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }
   
    private void checkIfGameOver(boolean ended, boolean winnerIsRed, boolean winnerIsBlue, boolean draw) {
        System.out.println(game.ended());
        System.out.println(game.winnerIsRed());
        System.out.println(game.winnerIsBlue());
        System.out.println(game.draw());
        assertEquals(ended, game.ended());
        assertEquals(winnerIsRed, game.winnerIsRed());
        assertEquals(winnerIsBlue, game.winnerIsBlue());
        assertEquals(draw, game.draw());
    }

    private void playingTester(int... columns) {
        IntStream.range( 0, columns.length ).forEach( numberOfToken -> { if ( numberOfToken % 2 == 0 ) { game.playRedAt( columns [ numberOfToken ] ); } 
        																else { game.playBlueAt( columns [ numberOfToken ] ); } } );
        }
    
    private void checkHowTheGameIsDeveloping (int height, int base, char currentGameMode, boolean ended, boolean fullBoard, boolean emptyBoard, int amountOfPiecesDisplayed){
        assertEquals(height, game.height);
        assertEquals(base, game.base);
        assertEquals(currentGameMode, game.getCurrentGameMode());
        assertEquals(ended, game.ended());
        assertEquals(fullBoard, game.fullBoard());
        assertEquals(emptyBoard, game.getAmountOfPiecesDisplayed() == 0);
        assertEquals(amountOfPiecesDisplayed, game.getAmountOfPiecesDisplayed());
    }
    
}