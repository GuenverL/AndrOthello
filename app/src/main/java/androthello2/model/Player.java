package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public abstract class Player{
    protected int id;
    protected static CellState color;
    protected static Board board;

    public Player(int id, CellState color,Board board) {
        this.id = id;
        this.color = color;
    }

    public int getId(){
        return this.id;
    }

    public int getCount(){
        return board.getColorCount(color);
    }

    public static void makeMove(Cell cell) {
        if( board.getLegalCells(color).contains(cell) ) {
            cell.move(color);
            board.capture(cell, color);
        }
    }
}
