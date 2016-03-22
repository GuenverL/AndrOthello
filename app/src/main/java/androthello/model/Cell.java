package androthello.model;

import java.util.ArrayList;

/**
 * Created by Strauss on 23/02/2016.
 */
public class Cell {
    private Board board;
    private CellColor color;
    private int row;
    private int col;

    public Cell(Board board, int row, int col){
        this.board = board;
        this.row = row;
        this.col = col;
        this.color = CellColorEmpty.getInstance();
    }

    public Cell(Board board, int row, int col, CellColor state){
        this.board = board;
        this.row = row;
        this.col = col;
        this.color = state;
    }

    public CellColor getColor() {
        return this.color;
    }

    public void move(CellColor state){
        this.color = state;
    }

    public Cell neighbor(int direction){
        switch(direction){
            case 1:
                return board.getCell(row+1, col-1);
            case 2:
                return board.getCell(row+1, col);
            case 3:
                return board.getCell(row+1, col+1);
            case 4:
                return board.getCell(row, col-1);
            case 6:
                return board.getCell(row, col+1);
            case 7:
                return board.getCell(row-1, col-1);
            case 8:
                return board.getCell(row-1, col);
            case 9:
                return board.getCell(row-1, col+1);
            default:
                return this;
        }
    }

    public CellColor neighborColor(int direction){
        if(this.neighbor(direction) != null)
            return this.neighbor(direction).getColor();
        return CellColorEmpty.getInstance();
    }

    public boolean isEmpty(){
        return color.isEmpty();
    }

    public boolean isLegal(CellColor color) {
        return this.isEmpty() && this.capture(color);
    }

    public ArrayList<Integer> checkNeighbors(CellColor color){
        ArrayList<Integer> directions = new ArrayList<>();
        for(int i = 1; i <= 9; i++){
            if(this.neighborColor(i) == color.opponentColor()){
                directions.add(i);
            }
        }
        return directions;
    }

    public ArrayList<Cell> capturedCells(CellColor color, int direction){
        ArrayList<Cell> cells = new ArrayList<>();
        Cell currentCell = this.neighbor(direction);
        cells.add(currentCell);
        while(currentCell.neighborColor(direction) == color.opponentColor()){
            currentCell = currentCell.neighbor(direction);
            cells.add(currentCell);
        }
        if(currentCell.neighborColor(direction) != color){
            cells.clear();
        }
        return cells;
    }

    public boolean capture(CellColor color) {
        ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
        for (int i : this.checkNeighbors(color)) {
            if(!this.capturedCells(color, i).isEmpty()) {
                cells.add(this.capturedCells(color, i));
            }
        }
        return !cells.isEmpty();
    }

    public void setState(CellColor state) {
        this.color = state;
    }
}