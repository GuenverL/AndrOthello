package androthello.model;

/**
 * Created by sunwel on 23/02/2016.
 */
public class Motor {
    private static Board board;
    private static Player[] players;
    private static Player activePlayer;
    private static CellState activePlayerColor;

    public Motor(int playerHumanNb){
        board = new Board();
        board.boardInitialize();

        players = new Player[2];
        players[0] = new PlayerUser(1, CellStateWhite.getInstance());

        if(playerHumanNb == 1){
            players[1] = new PlayerAI(2, CellStateBlack.getInstance());
        }else if(playerHumanNb == 2){
            players[1] = new PlayerUser(2, CellStateBlack.getInstance());
        }

        activePlayer = players[0];
        activePlayerColor = CellStateWhite.getInstance();
    }

    public static void makeMove(int row, int col){
        if( board.getLegalCells(activePlayerColor).contains(board.getCell(row, col)) ) {
            board.getCell(row, col).move(activePlayerColor);
            board.capture(row, col, activePlayerColor);
            endTurn();
        }
    }

    public static int getPlayerCount(int playerID){
        return players[playerID-1].getCount(board);
    }

    public static int getActivePlayerID(){
        return activePlayer.getId();
    }

    public static CellState GetCellState(int row, int col){
        return board.getCell(row, col).getState();
    }

    private static void endTurn(){
            playerChange();
    }

    private static boolean possibleMove(CellState color){
        return !board.getLegalCells(color).isEmpty();
    }

    private static void playerChange(){
        if(activePlayer.getId() == 1){
            activePlayer = players[1];
        }else{
            activePlayer = players[0];
        }
        activePlayerColor = activePlayerColor.opponentColor();
    }
}
