package androthello.model;

import java.io.*;

/**
 * Created by sunwel on 23/02/2016.
 * Motor class : contains the board and the players,
 * knows the number of human players the active player, his color and the color of the winner
 */
public class Motor {
    private static Board board;
    private static PlayerUser[] players;
    private static PlayerUser activePlayer;
    private static CellColor activePlayerColor;
    private static CellColor winner;
    private static int humanPlayersNumber;

    /**
     * Constructor, initialize the board and the players list
     * @param humanNumber the number of human players in the game
     */
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

    /**
     * Tells the player to make a move on the cell with the given coordinates
     * @param row the row of the cell
     * @param col the column of the cell
     */
    public void makeMove(int row, int col){
        if(activePlayer.makeMove(board.getCell(row,col)) == 0 )
            endTurn();
    }

    /**
     * Getter on the score of a player
     * @param playerID the player
     * @return int the score
     */
    public static int getPlayerCount(int playerID){
        return players[playerID-1].getCount(board);
    }

    /**
     * Getter on the id of the active player
      * @return int the id
     */
    public static int getActivePlayerID(){
        return activePlayer.getId();
    }

    /**
     * Getter on the color of a cell
     * @param row the row of the cell
     * @param col the column of the cell
     * @return CellColor the color of the cell
     */
    public static CellColor getCellColor(int row, int col){
        return board.getCell(row, col).getColor();
    }

    /**
     * Manages the end of turn : if the opponent has no move available, skip his turn;
     * if neither player can play, end the game and reset it.
     * */
    private void endTurn() {
        if (possibleMove(activePlayerColor.opponentColor())){
            winner = CellColorEmpty.getInstance();
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

    /**
     * Tells if a move is possible on the cell for the active player
     * @param color the color of the active player
     * @return boolean true if move possible, else false.
     */
    private static boolean possibleMove(CellColor color){
        return !board.getLegalCells(color).isEmpty();
    }

    /**
     * Changes the active player
     */
    private static void playerChange(){
        if(activePlayer.getId() == 1){
            activePlayer = players[1];
        }else{
            activePlayer = players[0];
        }
        activePlayerColor = activePlayerColor.opponentColor();
    }

    /**
     * Getter on the winner of the game
     * @return String the winner
     */
    public static String getWinner() {
        return winner.toString();
    }

    /**
     * Resets the game for two human players
     */
    public void resetGameUser(){
        board.boardInitialize();
        activePlayer = players[0];
        activePlayerColor = CellColorBlack.getInstance();
        players[1] = new PlayerUser(2, CellColorWhite.getInstance(), board);
    }

    /**
     * reset the game for one human players
     */
    public void resetGameAI(){
        board.boardInitialize();
        activePlayer = players[0];
        activePlayerColor = CellColorBlack.getInstance();
        players[1] = new PlayerAI(2, CellColorWhite.getInstance(), board);
    }

    /**
     * Saves the game : create a file if it doesn't exist and write the board as a string on it.
     * @throws IOException
     */
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

    /**
     * Laods a game : read the save file if it exists and builds the board from a string
     * @return int 0 if the file exists and is loaded, else false
     * @throws IOException
     */
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
