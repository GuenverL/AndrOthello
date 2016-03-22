package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 * The color White, contains an instance of the color for the Singleton pattern
 */
public class CellColorWhite implements CellColor {
    /**
     * Getter on the instance of the singleton
     */
    private static CellColorWhite CSWINSTANCE = new CellColorWhite();

    /**
     * Getter on the instance of the color
     * @return
     */
    public static CellColorWhite getInstance(){
        return CSWINSTANCE;
    }

    private CellColorWhite(){}

    /**
     * returns the opponent color
     * @return CellColor the Black color
     */
    public CellColor opponentColor(){
        return CellColorBlack.getInstance();
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
     * @return String "White"
     */
    public String toString(){
        return "White";
    }
}