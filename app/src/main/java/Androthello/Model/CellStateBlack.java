package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class CellStateBlack  implements CellState{
    private static CellStateBlack CSBINSTANCE = new CellStateBlack();

    public static CellStateBlack getInstance(){
        return CSBINSTANCE;
    }

    public CellState opponentColor(){
        return CellStateWhite.getInstance();
    }

    public boolean isEmpty() {
        return false;
    }
}
