package androthello.model;

import java.util.ArrayList;

/**
 * Created by sunwel on 02/02/2016.
 */
public class Board {

    public Cell[][] cell_matrix;

    //constructor
    public Board(){
        this.cell_matrix = new Cell[8][8];
    }

    public void boardInitialize(){
        for(int col = 0; col < 8; col++){
            for(int row = 0; row < 8; row++){
                this.cell_matrix[row][col] = new Cell(this, row, col);
            }
        }
        this.setCell(CellStateWhite.getInstance(), 3, 3);
        this.setCell(CellStateWhite.getInstance(), 4, 4);
        this.setCell(CellStateBlack.getInstance(), 3, 4);
        this.setCell(CellStateBlack.getInstance(), 4, 3);
    }

    public void setCell(CellState state, int row, int col){
        this.cell_matrix[row][col].setState(state);
    }

    public Cell getCell(int row, int col) {
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return null;
        }
        return this.cell_matrix[row][col];
    }

    public int getColorCount(CellState color){
        int count = 0;

        for(int col = 0; col <= 7; col++){
            for(int row = 0; row <= 7; row++){
                if(this.getCell(row, col).getState() == color){
                    count ++;
                }
            }
        }
        return count;
    }

    public ArrayList<Cell> getLegalCells(CellState color) {
        ArrayList<Cell> legalCells = new ArrayList<>();

        for(int col = 0; col <= 7; col++){
            for(int row = 0; row <= 7; row++){
                if(this.getCell(row, col).isLegal(color)){
                    legalCells.add(this.getCell(row, col));
                }
            }
        }

        return legalCells;
    }

    public void capture(Cell cell, CellState color){
        cell.move(color);
        for(int direction: cell.checkNeighbors(color)) {
            if(!cell.capturedCells(color, direction).isEmpty()){
                for (Cell c : cell.capturedCells(color, direction)) {
                    c.move(color);
                }
            }
        }
    }
}