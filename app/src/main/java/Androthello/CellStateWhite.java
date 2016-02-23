package Androthello;

/**
 * Created by Strauss on 23/02/2016.
 */
public class CellStateWhite implements CellState{
    private static CellStateWhite CSWINSTANCE = new CellStateWhite();

    public static CellStateWhite getInstance(){
        return CSWINSTANCE;
    }

    private CellStateWhite(){}

    public CellState white(){
        return this;
    }

    public CellState black(){
        return CellStateBlack.getInstance();
    }
    public int state(){
        return 2;
    }
}