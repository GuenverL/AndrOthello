package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 * The color Black, contains an instance of the color for the Singleton pattern
 */
public class CellColorBlack implements CellColor{
    private static CellColorBlack CSBINSTANCE = new CellColorBlack();

    /**
     * Getter on the instance of the color
     * @return
     */
    public static CellColorBlack getInstance(){
        return CSBINSTANCE;
    }

    /**
     * returns the opponent color
     * @return CellColor the White color
     */
    public CellColor opponentColor(){
        return CellColorWhite.getInstance();
    }

    /**
     * Checks if the cell is empty
     * @return false
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * ToString method
     * @return String "Black"
     */
    public String toString(){
        return "Black";
    }
}
