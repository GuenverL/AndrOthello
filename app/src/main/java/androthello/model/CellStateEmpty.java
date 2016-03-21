package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class CellStateEmpty  implements CellState{
    private static CellStateEmpty CSEINSTANCE = new CellStateEmpty();

    public static CellStateEmpty getInstance(){
        return CSEINSTANCE;
    }

    private CellStateEmpty(){}

    public CellState opponentColor() {
        return this;
    }

    public boolean isEmpty() {
        return true;
    }

    public String toString(){
        return "None";
    }
}
