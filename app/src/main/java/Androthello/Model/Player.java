package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public abstract class Player{
    protected int id;
    protected int color;

    public Player(int id, int color) {
        this.id = id;
        this.color = color;
    }

    public int getId(){
        return this.id;
    }

    public int getColor() {
        return this.color;
    }
}
