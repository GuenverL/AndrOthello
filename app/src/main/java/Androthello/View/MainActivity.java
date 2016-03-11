package androthello.view;

import android.content.Intent;
import android.graphics.PorterDuff;
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.strauss.androthello.R;

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



        Board board = new Board(); //Main board which carry all the cell's states


        table = (TableLayout) findViewById(R.id.grille_main);
        //table.setBackground(ContextCompat.getDrawable(this,R.drawable.velvet_green_background));
        for (int y = 0; y < TABLE_HEIGHT; y++) {
            final int row = y;
            TableRow r = new TableRow(this);
            table.addView(r);
            for (int x = 0; x < TABLE_WIDTH; x++) {
                final int col = x;

                ImageButton b = new ImageButton(this);
                b.setAdjustViewBounds(true);
                b.setScaleType(ImageView.ScaleType.FIT_XY );
                if((x == 3 && y == 3) || (x == 4 && y == 4))
                    b.getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.OVERLAY);
                else if((x == 4 && y == 3) || (x == 3 && y == 4))
                    b.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.OVERLAY);
                else b.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);



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




    }

    private void refresh_view(){

        //Refresh of the grid
        for (int y = 0; y < TABLE_HEIGHT; y++) {
            TableRow r = (TableRow)table.getChildAt(y);
            for (int x = 0; x < TABLE_WIDTH; x++) {

                changeColorFromState((ImageButton)r.getChildAt(x),Motor.GetCellState(y,x));
                // r.getChildAt(x).getBackground().setColorFilter(0x00000000, PorterDuff.Mode.OVERLAY);
            }
        }

        //Refresh the text of the counters buttons
        Button button_player_one = (Button)findViewById(R.id.button_player_one);
        button_player_one.setText(getResources().getString(R.string.player_one) + " : " + Motor.getPlayerCount(1)) ;
        Button button_player_two = (Button)findViewById(R.id.button_player_two);
        button_player_two.setText(getResources().getString(R.string.player_two) + " : " + Motor.getPlayerCount(2)) ;

        button_player_one.setClickable(false);
        button_player_two.setClickable(false);

        //Put the active player's button
        if(Motor.getActivePlayerID() == 1){
            button_player_one.setEnabled(true);
            button_player_two.setEnabled(false);
        }
        else{
            button_player_one.setEnabled(false);
            button_player_two.setEnabled(true);
        }
    }


    private void changeColorFromState(ImageButton button,CellState state){
        if(state == CellStateEmpty.getInstance())   button.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        else if(state == CellStateWhite.getInstance()) button.getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.OVERLAY);
        else if(state == CellStateBlack.getInstance()) button.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.OVERLAY);
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


}
