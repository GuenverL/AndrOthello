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

    public CellState white(){
        return CellStateWhite.getInstance();
    }

    public CellState black(){
        return CellStateBlack.getInstance();
    }

    public int color(){
        return 0;
    }
}
