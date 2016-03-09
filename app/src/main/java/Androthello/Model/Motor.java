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
        players[0] = new PlayerUser(0,1);

        if(playerHumanNb == 1){
            players[1] = new PlayerAI(1,2);
        }else if(playerHumanNb == 2){
            players[1] = new PlayerUser(1,2);
        }

        activePlayer = players[0];
        activePlayerColor = CellStateWhite.getInstance();
    }

    public static void makeMove(int row, int col){
        if( isLegalMove(row, col) ) {
            board.getCell(row, col).move(activePlayerColor);
            playerChange();
        }
    }

    public static int getPlayerCount(int playerID){
        return 2;
    }

    public static int getActivePlayerID(){
        return activePlayer.getId();
    }

    public static CellState GetCellState(int row, int col){
        return board.getCell(row, col).getState();
    }

    private static void playerChange(){
        if(activePlayer.getId() == 0){
            activePlayer = players[1];
        }else{
            activePlayer = players[0];
        }
        activePlayerColor = activePlayerColor.opponentColor();
    }

    private static boolean isLegalMove(int row, int col){
        if(!board.getCell(row, col).isEmpty()){
            return false;
        }

        return opNeighbor(row, col);
    }

    private static boolean opNeighbor(int row, int col){
        if(row == 0){
            if(col == 0){
                return (board.getCell(row, col).neighborColor(board, 2) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 3) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 6) == activePlayerColor.opponentColor());
            }else if(col == 7){
                return (board.getCell(row, col).neighborColor(board, 1) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 2) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 4) == activePlayerColor.opponentColor());
            }else{
                return (board.getCell(row, col).neighborColor(board, 1) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 2) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 3) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 4) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 6) == activePlayerColor.opponentColor());
            }
        }else if(row == 7){
            if(col == 0){
                return (board.getCell(row, col).neighborColor(board, 6) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 8) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 9) == activePlayerColor.opponentColor());
            }else if(col == 7){
                return (board.getCell(row, col).neighborColor(board, 4) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 7) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 8) == activePlayerColor.opponentColor());
            }else{
                return (board.getCell(row, col).neighborColor(board, 4) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 6) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 7) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 8) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 9) == activePlayerColor.opponentColor());
            }
        }else{
            if(col == 0){
                return (board.getCell(row, col).neighborColor(board, 2) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 3) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 6) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 8) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 9) == activePlayerColor.opponentColor());
            }else if(col == 7){
                return (board.getCell(row, col).neighborColor(board, 1) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 2) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 4) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 7) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 8) == activePlayerColor.opponentColor());
            }else{
                return (board.getCell(row, col).neighborColor(board, 1) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 2) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 3) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 4) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 6) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 7) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 8) == activePlayerColor.opponentColor() ||
                        board.getCell(row, col).neighborColor(board, 9) == activePlayerColor.opponentColor());
            }
        }
    }
}
