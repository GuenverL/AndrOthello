package androthello.model;

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
                this.setCell(new Cell(row, col), row, col);
            }
        }
        this.setCell(new Cell(3, 3, CellStateWhite.getInstance()), 3, 3);
        this.setCell(new Cell(4, 4, CellStateWhite.getInstance()), 4, 4);
        this.setCell(new Cell(3, 4, CellStateBlack.getInstance()), 3, 4);
        this.setCell(new Cell(4, 3, CellStateBlack.getInstance()), 4, 3);
    }

    public void setCell(Cell cell, int row, int col){
        this.cell_matrix[row][col] = cell;
    }

    public Cell getCell(int row, int col) {
        return this.cell_matrix[row][col];
    }
}
