package androthello.model;

import java.util.ArrayList;

/**
 * Created by sunwel on 02/02/2016.
 * The board of the game : contains the matrix of cells
 */
public class Board {

    public Cell[][] cell_matrix;

    /**
     * Constructor : construct a square matrix of cells with a size of 8
     */
    public Board(){
        this.cell_matrix = new Cell[8][8];
    }

    /**
     * Initializes the board : fill the matrix with empty cells and set the 4 central cells with black and white
     */
    public void boardInitialize(){
        for(int col = 0; col < 8; col++){
            for(int row = 0; row < 8; row++){
                this.cell_matrix[row][col] = new Cell(this, row, col);
            }
        }
        this.setCell(CellColorWhite.getInstance(), 3, 3);
        this.setCell(CellColorWhite.getInstance(), 4, 4);
        this.setCell(CellColorBlack.getInstance(), 3, 4);
        this.setCell(CellColorBlack.getInstance(), 4, 3);
    }

    /**
     * Setter on a cell
     * @param state the color of the cell
     * @param row the row of the cell
     * @param col the column of the cell
     */
    public void setCell(CellColor state, int row, int col){
        this.cell_matrix[row][col].setState(state);
    }

    /**
     * Getter on a cell
     * @param row row of the cell
     * @param col column of the cell
     * @return Cell the cell to return
     */
    public Cell getCell(int row, int col) {
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return null;
        }
        return this.cell_matrix[row][col];
    }

    /**
     * Getter on the number of cells of a given color
     * @param color the color of the cells
     * @return int the number of cells
     */
    public int getColorCount(CellColor color){
        int count = 0;

        for(int col = 0; col <= 7; col++){
            for(int row = 0; row <= 7; row++){
                if(this.getCell(row, col).getColor() == color){
                    count ++;
                }
            }
        }
        return count;
    }

    /**
     * Getter on the cells who are playable by a given color
     * @param color the color
     * @return ArrayList<Cell> the list of cell which are playable
     */
    public ArrayList<Cell> getLegalCells(CellColor color) {
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

    /**
     * Captures the cells on a move : set the color of the cell on which the player made a move,
     * and capture the cells capturable with this move
     * @param cell the cell on which the player made a move
     * @param color the color of the player who made the move
     */
    public void capture(Cell cell, CellColor color){
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
