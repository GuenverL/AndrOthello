package com.example.strauss.androthello;

import android.content.Context;
import android.widget.Button;

/**
 * Created by sunwel on 02/02/2016.
 */
public class Board {

    public Pawn[][] pawn_matrix;

    private char activePlayer;

    //constructor
    public Board() {
        pawn_matrix = new Pawn[8][8];
        activePlayer = 'b';
    }

    public void makeaMove(int x,int y){
        pawn_matrix[y][x].flip(activePlayer);
        activePlayer = Pawn.oppositeColor(activePlayer);
    }

    public char getActivePlayer() {
        return activePlayer;
    }
}
