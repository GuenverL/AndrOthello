package com.example.strauss.androthello;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.Button;

/**
 * Created by sunwel on 02/02/2016.
 */
public class Pawn {

    private char state;
    private Button button;

    //constructor
    public Pawn(Context context) {
        state = 'e';
        button = new Button(context);
    }

    public Pawn(Context context, char s){
        state = s;
        button = new Button(context);
        if (state == 'w') {
            button.setBackgroundColor(Color.WHITE);
        } else if (state == 'b'){
            button.setBackgroundColor(Color.BLACK);
        }

    }

    public static char oppositeColor(char c){
        if(c == 'b') return 'w';
        else if(c == 'w') return 'b';
        else return 'e';
    }

    // methods
    public void setState(char c) {
        state = c;
    }

    public char getState(){return state;};

    public void flip(char player){
        if (state == 'b') {
            button.setBackgroundColor(Color.WHITE);
            state = 'w';
        } else if(state == 'w'){
            button.setBackgroundColor(Color.BLACK);
            state = 'b';
        }
        else{
            state = oppositeColor(player);
            if (state == 'w') {
                button.setBackgroundColor(Color.WHITE);
            } else if(state == 'b'){
                button.setBackgroundColor(Color.BLACK);
            }
        }
    }



    public Button getButton(){
        return button;
    }
}
