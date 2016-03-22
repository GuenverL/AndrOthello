package androthello.model;

/**
 * Created by Strauss on 23/02/2016.
 * The player who plays the game, owns an id, a color and knows the board
 */
public class PlayerUser {
    protected int id;
    protected CellColor color;
    protected Board board;

    /**
     * Constructor of the player
     * @param id the id of the player
     * @param color the color of the player
     * @param board the board of the game
     */
    public PlayerUser(int id, CellColor color, Board board) {
        this.id = id;
        this.color = color;
        this.board = board;
    }

    /**
     * getter on the id
     * @return
     */
    public int getId(){
        return this.id;
    }

    /**
     * getter on the score of the player
     * @return int the score of the player
     */
    public int getCount(){
        return board.getColorCount(color);
    }

    /**
     * Makes a move without parameters for the PlayerAI
     */
    public void makeMove(){}

    /**
     * Majkes a move on a given cell
     * @param cell the cell on which the player make the move
     * @return int return 0 if the player can make a move, else 1
     */
    public int makeMove(Cell cell){
        if (board.getLegalCells(color).contains(cell)) {
            board.capture(cell, color);
            return 0;
        }
        return 1;
    }
}
