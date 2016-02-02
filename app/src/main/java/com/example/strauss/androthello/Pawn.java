package com.example.strauss.androthello;

import android.content.Context;
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

    // methods
    public void setState(char c) {
        state = c;
    }

    public char getState(){return state;};

    public void flip(){
        if (state == 'b') {
           button.getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.OVERLAY);
            state = 'w';
        } else {button.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.OVERLAY); state = 'b';
        }
    }

    public Button getButton(){
        return button;
    }
}
