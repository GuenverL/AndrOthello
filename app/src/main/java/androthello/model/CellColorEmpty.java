package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 * The color Empty, contains an instance of the color for the Singleton pattern
 */
public class CellColorEmpty implements CellColor {
    /**
     * Getter on the instance of the singleton
     */
    private static CellColorEmpty CSEINSTANCE = new CellColorEmpty();

    /**
     * Getter on the instance of the color
     * @return
     */
    public static CellColorEmpty getInstance(){
        return CSEINSTANCE;
    }

    private CellColorEmpty(){}

    /**
     * returns the opponent color
     * @return CellColor the Empty color
     */
    public CellColor opponentColor() {
        return this;
    }

    /**
     * Checks if the cell is empty
     * @return true
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * ToString method
     * @return String "None"
     */
    public String toString(){
        return "None";
    }
}
