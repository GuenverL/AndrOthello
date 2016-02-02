package com.example.strauss.androthello;

import android.content.Context;
import android.widget.Button;

/**
 * Created by sunwel on 02/02/2016.
 */
public class Board {

    public Pawn[][] pawn_matrix;

    //constructor
    public Board() {
        pawn_matrix = new Pawn[8][8];
    }
}
