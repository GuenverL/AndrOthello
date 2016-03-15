package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class PlayerAI extends Player{
    public PlayerAI(int id, CellState color, Board board) {
        super(id,color, board);
    }

    public static void makeMove() {
        makeMove(board.getLegalCells(color).get(0));
    }
}
