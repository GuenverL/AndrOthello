package androthello.model;

/**
 * Created by sunwel on 23/02/2016.
 */
public class Motor {
    private static Board board;
    private Player[] players;
    private static Player activePlayer;

    public Motor(int playerHumanNb){
        board = new Board();
        board.boardInitialize();

        this.players = new Player[2];
        this.players[0] = new PlayerUser(1);

        if(playerHumanNb == 1){
            this.players[1] = new PlayerAI(2);
        }else if(playerHumanNb == 2){
            this.players[1] = new PlayerUser(2);
        }

        activePlayer = players[0];
    }

    public static void MakeMove(int col, int row){
        int color = activePlayer.getColor();
        if(color == 1) {
            board.getCell(0, 0).white();
        }
        else{
            board.getCell(col, row).black();
        }
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
}
