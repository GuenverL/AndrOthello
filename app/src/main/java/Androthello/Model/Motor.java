package androthello.model;

/**
 * Created by sunwel on 23/02/2016.
 */
public class Motor {

    public static void MakeMove(int col, int row){

    }

    public static int getPlayerCount(int playerID){
        return 2;
    }

    public static int getActivePlayerID(){
        return 1;
    }

    public static CellState GetCellState(int col,int row){
        return CellStateEmpty.getInstance();
    }
}
