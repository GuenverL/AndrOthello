package androthello.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Strauss on 23/02/2016.
 */
public class PlayerAI extends PlayerUser {
    public PlayerAI(int id, CellColor color, Board board) {
        super(id, color, board);
    }

    @Override
    public void makeMove(){
        Random randomGenerator = new Random();
        ArrayList<Cell> legalList = board.getLegalCells(color);
        if(legalList.size()>0){
            int random = randomGenerator.nextInt(legalList.size());
            board.capture(legalList.get(random), color);
        }
    }

    @Override
    public int makeMove(Cell cell){
        return 0;
    }
}
