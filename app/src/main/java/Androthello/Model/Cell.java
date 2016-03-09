package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class Cell {

    private CellState state;
    private int row;
    private int col;

    public Cell(int row, int col){
        this.state = CellStateEmpty.getInstance();
        this.row = row;
        this.col = col;
    }

    public Cell(int row, int col, CellState state){
        this.row = row;
        this.col = col;
        this.state = state;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public CellState getState() {
        return this.state;
    }

    public Cell neighbor(Board board,int direction){
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

    public CellState neighborColor(Board board ,int direction){
        return this.neighbor(board, direction).getState();
    }

    public boolean isEmpty(){
        return state.isEmpty();
    }

    public void move(CellState state){
        this.state = state;
    }
}