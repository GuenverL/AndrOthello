package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class CellStateBlack  implements CellState{
    private static CellStateBlack CSBINSTANCE = new CellStateBlack();

    public static CellStateBlack getInstance(){
        return CSBINSTANCE;
    }

    private CellStateBlack(){}

    public CellState white(){
        return CellStateWhite.getInstance();
    }

    public CellState black(){
        return this;
    }

    public int color(){
        return 2;
    }
}
