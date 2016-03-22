package androthello.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.strauss.androthello.R;

import java.io.IOException;

import androthello.model.Board;
import androthello.model.CellState;
import androthello.model.CellStateBlack;
import androthello.model.CellStateEmpty;
import androthello.model.CellStateWhite;
import androthello.model.Motor;


public class MainActivity extends AppCompatActivity {

    TableLayout table;
    Motor motor;
    Button button_player_one;
    Button button_player_two;
    private static final int TABLE_WIDTH = 8;
    private static final int TABLE_HEIGHT = 8;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        motor = new Motor(2);







        //Setting button
        Button b_settings = (Button) (findViewById(R.id.button_settings ));
        b_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsButtonPressed();

            }
        });

        Button b_newgame = (Button) (findViewById(R.id.button_newgame ));
        b_newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                motor = new Motor(2);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                        refresh_view();
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Êtes-vous sûr ?").setPositiveButton("Oui", dialogClickListener)
                        .setNegativeButton("Non", dialogClickListener).show();
            }
        });

        Button b_newgameAI = (Button) (findViewById(R.id.button_newgameAI ));
        b_newgameAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                motor = new Motor(1);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                        refresh_view();
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Êtes-vous sûr ?").setPositiveButton("Oui", dialogClickListener)
                        .setNegativeButton("Non", dialogClickListener).show();
            }

        });









        table = (TableLayout) findViewById(R.id.grille_main);
        //table.setBackground(ContextCompat.getDrawable(this,R.drawable.velvet_green_background));
        for (int y = 0; y < TABLE_HEIGHT; y++) {
            final int row = y;
            TableRow r = new TableRow(this);
            table.addView(r);
            for (int x = 0; x < TABLE_WIDTH; x++) {
                final int col = x;

                ImageButton b = new ImageButton(this){
                    @Override
                    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
                        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
                        this.setMeasuredDimension(parentWidth, parentWidth);
                    }
                };



                Drawable roundDrawable = getResources().getDrawable(R.drawable.button_round);


                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    b.setBackgroundDrawable(roundDrawable);
                } else {
                    b.setBackground(roundDrawable);
                }

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Toast.makeText(getApplicationContext(),
                                "You clicked (" + row + "," + col + ")",
                                Toast.LENGTH_SHORT).show();*/

                        motor.makeMove(row, col);
                        refresh_view();
                    }
                });
                r.addView(b);
            }

        }
        refresh_view();



    }

    private void refresh_view(){

        if(Motor.isEndedGame()){
            Toast.makeText(getApplicationContext(),
                    "YOU WIN ! FLAWLESS VICTORY ",
                    Toast.LENGTH_SHORT).show();
        }

        //Refresh of the grid
        for (int y = 0; y < TABLE_HEIGHT; y++) {
            TableRow r = (TableRow)table.getChildAt(y);
            for (int x = 0; x < TABLE_WIDTH; x++) {

                changeColorFromState((ImageButton)r.getChildAt(x),Motor.GetCellState(y,x));
            }
        }



        //Refres5h the text of the counters buttons
        Button button_player_one = (Button)findViewById(R.id.button_player_one);
        button_player_one.setText(getResources().getString(R.string.player_one) + " : " + Motor.getPlayerCount(1)) ;
        button_player_one.getBackground().setColorFilter(0xFF000000, PorterDuff.Mode.SRC_ATOP);
        button_player_one.setTextColor(0xFFFFFFFF);

        Button button_player_two = (Button)findViewById(R.id.button_player_two);
        button_player_two.setText(getResources().getString(R.string.player_two) + " : " + Motor.getPlayerCount(2)) ;
        button_player_two.getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
        button_player_two.setTextColor(0xFF000000);


        button_player_one.setClickable(false);
        button_player_two.setClickable(false);

        //Put the active player's button
        if(Motor.getActivePlayerID() == 1){
            button_player_one.setAlpha(1);
            button_player_two.setAlpha((float)0.3);
        }
        else{
            button_player_one.setAlpha((float) 0.3);
            button_player_two.setAlpha(1);
        }
    }


    private void changeColorFromState(ImageButton button,CellState state){
        if(state == CellStateEmpty.getInstance())   button.getBackground().setColorFilter(0x01000000, PorterDuff.Mode.MULTIPLY);
        else if(state == CellStateWhite.getInstance()) setButtonColorWhite(button);
        else if(state == CellStateBlack.getInstance()) setButtonColorBlack(button);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(this, SettingsActivity.class);
            startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onOptionsButtonPressed(){
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }



    public void onPause()
    {
        super.onPause();
        try {
            Motor.saveGame();
            Toast.makeText(getApplicationContext(),
                    "  saved game",
                    Toast.LENGTH_SHORT).show();
            refresh_view();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onResume(){
        super.onResume();
        try {
            Motor.loadGame();
            Toast.makeText(getApplicationContext(),
                    "loaded game",
                    Toast.LENGTH_SHORT).show();
            refresh_view();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setButtonColorWhite(ImageButton button){
        button.getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
    }

    private void setButtonColorBlack(ImageButton button){
        button.getBackground().setColorFilter(0xFF000000, PorterDuff.Mode.SRC_ATOP);
    }



}
