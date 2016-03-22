package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class CellColorEmpty implements CellColor {
    private static CellColorEmpty CSEINSTANCE = new CellColorEmpty();

    public static CellColorEmpty getInstance(){
        return CSEINSTANCE;
    }

    private CellColorEmpty(){}

    public CellColor opponentColor() {
        return this;
    }

    public boolean isEmpty() {
        return true;
    }

    public String toString(){
        return "None";
    }
}
