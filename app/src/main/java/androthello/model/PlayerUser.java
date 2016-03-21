package androthello.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Strauss on 23/02/2016.
 */
public class PlayerUser {
    protected int id;
    protected CellState color;
    protected Board board;

    public PlayerUser(int id, CellState color, Board board) {
        this.id = id;
        this.color = color;
        this.board = board;
    }

    public int getId(){
        return this.id;
    }

    public int getCount(Board board){
        return board.getColorCount(color);
    }

    public void makeMove(){}

    public int makeMove(Cell cell){
        if (board.getLegalCells(color).contains(cell)) {
            board.capture(cell, color);
            return 0;
        }
        return 1;
    }
}
