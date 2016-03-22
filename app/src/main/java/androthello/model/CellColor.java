package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 */
public interface CellColor {
    CellColor opponentColor();
    boolean isEmpty();
    String toString();
}
