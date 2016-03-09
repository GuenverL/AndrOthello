package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class CellStateWhite implements CellState{
    private static CellStateWhite CSWINSTANCE = new CellStateWhite();

    public static CellStateWhite getInstance(){
        return CSWINSTANCE;
    }

    private CellStateWhite(){}

    public CellState opponentColor(){
        return CellStateBlack.getInstance();
    }

    public boolean isEmpty() {
        return false;
    }
}