package Androthello;

/**
 * Created by Strauss on 23/02/2016.
 */
public class Cell {
    private CellState state;
    private int coordX;
    private int coordY;

    public Cell(int x, int y){
        this.state = CellStateEmpty.getInstance();
        this.coordX = x;
        this.coordY = y;
    }

    public void white(){
        this.state = state.white();
    }

    public void black(){
        this.state = state.black();
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
}