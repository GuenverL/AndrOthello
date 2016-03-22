package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 * The color of the cells
 */
public interface CellColor {
    /**
     * Getter on the opponent color
     * @return CellColor the opponent color
     */
    CellColor opponentColor();

    /**
     * checks if the cell is empty
     * @return boolean, true if the cell is empty, else false
     */
    boolean isEmpty();

    /**
     * toString
     * @return String the color formatted to string
     */
    String toString();
}
