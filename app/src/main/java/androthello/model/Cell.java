package androthello.model;

import java.util.ArrayList;

/**
 * Created by Strauss on 23/02/2016.
 * The cell of the game :knows tha board, its color, row and column
 */
public class Cell {
    private Board board;
    private CellColor color;
    private int row;
    private int col;

    /**
     *Constructor with 3 parameters, set the color on empty by default
     * @param board the board of the game
     * @param row the row of the cell
     * @param col the column of the cell
     */
    public Cell(Board board, int row, int col){
        this.board = board;
        this.row = row;
        this.col = col;
        this.color = CellColorEmpty.getInstance();
    }

    /**
     *Constructor with 4 parameters
     * @param board the board of the game
     * @param row the row of the cell
     * @param col the column of the cell
     * @param color the color of the cell
     */
    public Cell(Board board, int row, int col, CellColor color){
        this.board = board;
        this.row = row;
        this.col = col;
        this.color = color;
    }

    /**
     * Getter on the color
     * @return CellColor the color of the cell
     */
    public CellColor getColor() {
        return this.color;
    }

    /**
     * Make a move on the cell : change its color
     * @param state he color to set
     */
    public void move(CellColor state){
        this.color = state;
    }

    /**
     * Getter on the neighbor of the cell in a given direction
     * @param direction the direction
     * @return Cell the neighbor
     */
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

    /**
     * Getter on the color of the neighbor in a given direction
     * @param direction the direction
     * @return CellColor the color of the neighbor
     */
    public CellColor neighborColor(int direction){
        if(this.neighbor(direction) != null)
            return this.neighbor(direction).getColor();
        return CellColorEmpty.getInstance();
    }

    /**
     * returns true if the cell is empty, else false
     * @return boolean
     */
    public boolean isEmpty(){
        return color.isEmpty();
    }

    /**
     * checks if a move on the cell is legal for a given color
     * @param color the color who ants to make the move
     * @return boolean true if the move is legal, else false
     */
    public boolean isLegal(CellColor color) {
        return this.isEmpty() && this.capture(color);
    }

    /**
     * Checks the colors of the neighbors to determine the directions in which there is e opponent cell
     * @param color the color of the playing player
     * @return ArrayList the list on directions
     */
    public ArrayList<Integer> checkNeighbors(CellColor color){
        ArrayList<Integer> directions = new ArrayList<>();
        for(int i = 1; i <= 9; i++){
            if(this.neighborColor(i) == color.opponentColor()){
                directions.add(i);
            }
        }
        return directions;
    }

    /**
     * Constructs a list of the cells captured by a move in a given direction by a given color
     * @param color the color
     * @param direction the direction
     * @return ArrayList the list of cells captured
     */
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

    /**
     * checks if there is a capture cells on a move by a given color
     * @param color the color of the player making the move
     * @return boolean true if there is a capture, else false
     */
    public boolean capture(CellColor color) {
        ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
        for (int i : this.checkNeighbors(color)) {
            if(!this.capturedCells(color, i).isEmpty()) {
                cells.add(this.capturedCells(color, i));
            }
        }
        return !cells.isEmpty();
    }

    /**
     * Setter on the color of the cell
     * @param color the color to set
     */
    public void setState(CellColor color) {
        this.color = color;
    }
}