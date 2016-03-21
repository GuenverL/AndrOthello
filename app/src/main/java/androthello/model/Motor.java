package androthello.model;

import java.io.*;

/**
 * Created by sunwel on 23/02/2016.
 */
public class Motor {
    private static Board board;
    private static PlayerUser[] players;
    private static PlayerUser activePlayer;
    private static CellState activePlayerColor;
    private static CellState winner;
    private static int humanPlayersNumber;

    public Motor(int humanNumber){
        board = new Board();
        board.boardInitialize();
        humanPlayersNumber = humanNumber;
        winner = CellStateEmpty.getInstance();

        players = new PlayerUser[2];
        players[0] = new PlayerUser(1, CellStateWhite.getInstance(), board);

        if(humanPlayersNumber == 1){
            players[1] = new PlayerAI(2, CellStateBlack.getInstance(), board);
        }else if(humanPlayersNumber == 2){
            players[1] = new PlayerUser(2, CellStateBlack.getInstance(), board);
        }

        activePlayer = players[0];
        activePlayerColor = CellStateBlack.getInstance();
    }

    public static void makeMove(int row, int col){
        if(activePlayer.makeMove(board.getCell(row,col)) == 0 )
            endTurn();
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
        if (possibleMove(activePlayerColor.opponentColor())){
            playerChange();
        }else{
            if(!possibleMove(activePlayerColor))
            winner = activePlayerColor;
            resetGame();
        }
        if(activePlayer.getId() == 2 && humanPlayersNumber == 1){
            activePlayer.makeMove();
            endTurn();
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

    public String isEndedGame() {
        return winner.toString();
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

        if(activePlayerColor == CellStateBlack.getInstance()){
            saveState += "0";
        }else{
            saveState += "1";
        }

        for(int row = 0; row < 8; row ++){
            for(int col = 0; col < 8; col ++){
                CellState state = board.getCell(row, col).getState();
                if(state.equals(CellStateEmpty.getInstance())) {
                    saveState += '0';
                }else if(state.equals(CellStateBlack.getInstance())) {
                    saveState += '1';
                }else if(state.equals(CellStateWhite.getInstance())) {
                    saveState += '2';
                }
            }
        }

        bufferedwriter.write(saveState);
        bufferedwriter.close();
    }

    public static int loadGame() throws IOException {
        String fileName = "save.txt";
        String saveState ;

        try{
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            saveState = bufferedReader.readLine();

            if(saveState.charAt(0) == '0'){
                activePlayerColor = CellStateBlack.getInstance();
            }else{
                activePlayerColor = CellStateWhite.getInstance();
            }

            int indice = 1;

            for(int row = 0; row < 8; row ++){
                for(int col = 0; col < 8; col ++){
                    switch(saveState.charAt(indice)){
                        case '0':
                            board.setCell(CellStateEmpty.getInstance(),row , col);
                            break;
                        case '1':
                            board.setCell(CellStateBlack.getInstance(),row , col);
                            break;
                        case '2':
                            board.setCell(CellStateWhite.getInstance(),row , col);
                            break;
                    }
                }
            }

            bufferedReader.close();
            return 0;

        }catch(FileNotFoundException exception){
            return 1;
        }
    }
}
