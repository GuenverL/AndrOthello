package androthello.model;

/**
 * Created by sunwel on 23/02/2016.
 */
public class Motor {
    private static Board board;
    private static Player[] players;
    private static Player activePlayer;

    public Motor(int playerHumanNb){
        board = new Board();
        board.boardInitialize();

        players = new Player[2];
        players[0] = new PlayerUser(0,1);

        if(playerHumanNb == 1){
            players[1] = new PlayerAI(1,2);
        }else if(playerHumanNb == 2){
            players[1] = new PlayerUser(1,2);
        }

        activePlayer = players[0];
    }

    public static void makeMove(int col, int row){
        int color = activePlayer.getColor();
        if(color == 1) {
            board.getCell(col, row).white();
        }
        else{
            board.getCell(col, row).black();
        }
        playerChange();
    }

    public static int getPlayerCount(int playerID){
        return 2;
    }

    public static int getActivePlayerID(){
        return activePlayer.getId();
    }

    public static CellState GetCellState(int col,int row){
        return board.getCell(col,row).getState();
    }

    public static void playerChange(){
        if(activePlayer.getId() == 0){
            activePlayer = players[1];
        }else{
            activePlayer = players[0];
        }
    }
}
