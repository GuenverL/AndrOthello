package androthello.model;

import java.io.*;

/**
 * Created by sunwel on 23/02/2016.
 */
public class Motor {
    private static Board board;
    private static Player[] players;
    private static Player activePlayer;
    private static CellState activePlayerColor;
    private static boolean endedGame;

    public Motor(int playerHumanNb){
        board = new Board();
        board.boardInitialize();

        endedGame = false;

        players = new Player[2];
        players[0] = new PlayerUser(1, CellStateWhite.getInstance());

        if(playerHumanNb == 1){
            players[1] = new PlayerAI(2, CellStateBlack.getInstance());
        }else if(playerHumanNb == 2){
            players[1] = new PlayerUser(2, CellStateBlack.getInstance());
        }

        activePlayer = players[0];
        activePlayerColor = CellStateBlack.getInstance();
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

    private static void endTurn() {
        if (possibleMove(activePlayerColor)){
            playerChange();
        }else{
            endedGame = true;
            resetGame();
        }
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

    public static boolean isEndedGame() {
        return endedGame;
    }

    public static void resetGame(){
        board = new Board();
        board.boardInitialize();
    }

    public static void saveGame() throws IOException {
        String fileName = "save.txt";
        PrintWriter writer = new PrintWriter(fileName);
        String saveState = "";

        BufferedWriter bufferedwriter = new BufferedWriter(writer);

        if(activePlayerColor == CellStateWhite.getInstance()){
            saveState += "1";
        }else{
            saveState += "0";
        }

        for(int row = 0; row < 8; row ++){
            for(int col = 0; col < 8; col ++){
                CellState state = board.getCell(row, col).getState();
                if(state.equals(CellStateBlack.getInstance())) {
                    saveState += "1";
                }else if(state.equals(CellStateWhite.getInstance())) {
                    saveState += "2";
                }else if(state.equals(CellStateEmpty.getInstance())) {
                    saveState += "0";
                }
            }
        }

        bufferedwriter.write(saveState);

        bufferedwriter.close();
    }

    public static void loadGame() throws IOException {
        String fileName = "save.txt";
        String saveState = "";
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            saveState = bufferedReader.readLine();
            bufferedReader.close();
        }catch(FileNotFoundException exception){}
        catch(IOException exception){}
    }
}
