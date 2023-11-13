package Linea;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Printer {

    Linea board;

    public Printer(Linea board) {
        this.board = board;
    }

    public String show() {
    	
        String top = getLine("┌", "──", "┬", "┐");
        
        String body = IntStream.range(0, board.height).mapToObj(row -> IntStream.range(0, board.base).mapToObj(column -> board.getPieceAt(column, board.height - row - 1))
													  .collect(Collectors.joining("│", "│", "│\n"))).collect(Collectors.joining());
        
        String horizontalLine = getLine("├", "──", "┼", "┤");
        
        String columnNumbers = "│" + IntStream.range(1, board.base + 1).mapToObj(column -> column >= 10 ?  Integer.toString(column) :  column + " ").collect(Collectors.joining("│")) + "│\n";
        
        String bottom = getLine("└", "──", "┴", "┘");

        String currentGameState = "State: " + this.board.getCurrentGameState().show() + "\n";

        return top + body + horizontalLine + columnNumbers + bottom + currentGameState;
    }

    public String getLine(String left, String middle, String intersection, String right) {
        return left + IntStream.range( 0, board.base ).mapToObj( column -> middle ).collect( Collectors.joining(intersection) ) + right + "\n";
    }
}
