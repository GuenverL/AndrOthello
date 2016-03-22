package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class CellColorWhite implements CellColor {
    private static CellColorWhite CSWINSTANCE = new CellColorWhite();

    public static CellColorWhite getInstance(){
        return CSWINSTANCE;
    }

    private CellColorWhite(){}

    public CellColor opponentColor(){
        return CellColorBlack.getInstance();
    }

    public boolean isEmpty() {
        return false;
    }

    public String toString(){
        return "White";
    }
}