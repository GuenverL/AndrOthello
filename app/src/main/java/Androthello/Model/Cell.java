package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class Cell {

    private CellState state;
    private int col;
    private int row;

    public Cell(int col, int row){
        this.state = CellStateEmpty.getInstance();
        this.col = col;
        this.row = row;
    }

    public void white(){
        this.state = this.state.white();
    }

    public void black(){
        this.state = this.state.black();
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public CellState getState() {
        return this.state;
    }
}