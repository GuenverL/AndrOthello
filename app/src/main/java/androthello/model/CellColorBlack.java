package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public class CellColorBlack implements CellColor {
    private static CellColorBlack CSBINSTANCE = new CellColorBlack();

    public static CellColorBlack getInstance(){
        return CSBINSTANCE;
    }

    public CellColor opponentColor(){
        return CellColorWhite.getInstance();
    }

    public boolean isEmpty() {
        return false;
    }

    public String toString(){
        return "Black";
    }
}
