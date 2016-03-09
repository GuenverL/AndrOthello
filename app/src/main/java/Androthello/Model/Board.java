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
                this.setCell(new Cell(col, row), col, row);
            }
        }
        this.cell_matrix[4][4].white();
        this.cell_matrix[5][5].white();
        this.cell_matrix[4][5].black();
        this.cell_matrix[5][4].black();
    }

    public void setCell(Cell cell, int col, int row){
        this.cell_matrix[col][row] = cell;
    }

    public Cell getCell(int col, int row) {
        return this.cell_matrix[col][row];
    }
}
