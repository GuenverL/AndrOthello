package com.example.strauss.androthello;

import android.content.Context;
import android.widget.Button;

/**
 * Created by sunwel on 02/02/2016.
 */
public class Board {

    public Cell[][] cell_matrix;

    //constructor
    public Board(){
        cell_matrix = new Cell[8][8];
    }

    public void boardInitialize(){
        int cX;
        int cY;

        for(cX = 0; cX < 8; cX++){
            for(cY = 0; cY < 8; cY++){
                this.setCell(new Cell(cX, cY), cX, cY);
            }
        }
        cell_matrix[4][4].white();
        cell_matrix[5][5].white();
        cell_matrix[4][5].black();
        cell_matrix[5][4].black();
    }

    public void setCell(Cell cell, int x, int y){
        cell_matrix[x][y] = cell;
    }

}
