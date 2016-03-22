package androthello.model;

import java.io.*;

/**
 * Created by sunwel on 23/02/2016.
 */
public class Motor {
    private static Board board;
    private static PlayerUser[] players;
    private static PlayerUser activePlayer;
    private static CellColor activePlayerColor;
    private static CellColor winner;
    private static int humanPlayersNumber;

    public Motor(int humanNumber){
        board = new Board();
        board.boardInitialize();
        humanPlayersNumber = humanNumber;
        winner = CellColorEmpty.getInstance();

        players = new PlayerUser[2];
        players[0] = new PlayerUser(1, CellColorBlack.getInstance(), board);

        if(humanPlayersNumber == 1){
            players[1] = new PlayerAI(2, CellColorBlack.getInstance(), board);
        }else if(humanPlayersNumber == 2){
            players[1] = new PlayerUser(2, CellColorWhite.getInstance(), board);
        }

        activePlayer = players[0];
        activePlayerColor = CellColorBlack.getInstance();
    }

    public void makeMove(int row, int col){
        if(activePlayer.makeMove(board.getCell(row,col)) == 0 )
            endTurn();
    }

    public static int getPlayerCount(int playerID){
        return players[playerID-1].getCount(board);
    }

    public static int getActivePlayerID(){
        return activePlayer.getId();
    }

    public static CellColor getCellColor(int row, int col){
        return board.getCell(row, col).getColor();
    }

    private void endTurn() {
        if (possibleMove(activePlayerColor.opponentColor())){
            playerChange();
        }else{
            if(!possibleMove(activePlayerColor))
                winner = activePlayerColor;
            if(humanPlayersNumber == 2){
                resetGameUser();
            }else{
                resetGameAI();
            }
        }
        if(activePlayer.getId() == 2 && humanPlayersNumber == 1){
            activePlayer.makeMove();
            endTurn();
        }
    }

    private static boolean possibleMove(CellColor color){
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

    public static String getWinner() {
        return winner.toString();
    }

    public void resetGameUser(){
        board.boardInitialize();
        activePlayer = players[0];
        activePlayerColor = CellColorBlack.getInstance();
        players[1] = new PlayerUser(2, CellColorWhite.getInstance(), board);
    }

    public void resetGameAI(){
        board.boardInitialize();
        activePlayer = players[0];
        activePlayerColor = CellColorBlack.getInstance();
        players[1] = new PlayerAI(2, CellColorWhite.getInstance(), board);
    }

    public static Boolean isEndedGame(){
        return (winner != CellColorEmpty.getInstance());
    }

    public static void saveGame() throws IOException {
        String fileName = "save.txt";
        PrintWriter writer = new PrintWriter(fileName);
        String saveState = "";

        BufferedWriter bufferedwriter = new BufferedWriter(writer);

        if(activePlayerColor == CellColorBlack.getInstance()){
            saveState += "0";
        }else{
            saveState += "1";
        }

        for(int row = 0; row < 8; row ++){
            for(int col = 0; col < 8; col ++){
                CellColor state = board.getCell(row, col).getColor();
                if(state.equals(CellColorEmpty.getInstance())) {
                    saveState += '0';
                }else if(state.equals(CellColorBlack.getInstance())) {
                    saveState += '1';
                }else if(state.equals(CellColorWhite.getInstance())) {
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
                activePlayerColor = CellColorBlack.getInstance();
            }else{
                activePlayerColor = CellColorWhite.getInstance();
            }

            int indice = 1;

            for(int row = 0; row < 8; row ++){
                for(int col = 0; col < 8; col ++){
                    switch(saveState.charAt(indice)){
                        case '0':
                            board.setCell(CellColorEmpty.getInstance(),row , col);
                            break;
                        case '1':
                            board.setCell(CellColorBlack.getInstance(),row , col);
                            break;
                        case '2':
                            board.setCell(CellColorWhite.getInstance(),row , col);
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
