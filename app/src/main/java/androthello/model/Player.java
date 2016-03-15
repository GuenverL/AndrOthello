package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public abstract class Player{
    protected int id;
    protected CellState color;

    public Player(int id, CellState color) {
        this.id = id;
        this.color = color;
    }

    public int getId(){
        return this.id;
    }

    public int getCount(Board board){
        return board.getColorCount(color);
    }
}
