package com.example.strauss.androthello;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TableLayout table;
    private static final int TABLE_WIDTH = 8;
    private static final int TABLE_HEIGHT = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        table = (TableLayout) findViewById(R.id.grille_main);
        table.setBackground(ContextCompat.getDrawable(this,R.drawable.velvet_green_background));
        for (int y = 0; y < TABLE_HEIGHT; y++) {
            final int row = y;
            TableRow r = new TableRow(this);
            table.addView(r);
            for (int x = 0; x < TABLE_WIDTH; x++) {
                final int col = x;
                Button b = new Button(this);
                b.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),
                                "You clicked (" + row + "," + col + ")",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                r.addView(b);
            }
        }*/

        final Board board = new Board();

        table = (TableLayout) findViewById(R.id.grille_main);
        table.setBackground(ContextCompat.getDrawable(this,R.drawable.velvet_green_background));
        for (int y = 0; y < TABLE_HEIGHT; y++) {
            final int row = y;
            TableRow r = new TableRow(this);
            table.addView(r);
            for (int x = 0; x < TABLE_WIDTH; x++) {
                final int col = x ;
                if((x == 3 && y == 3) || (x == 4 && y == 4))
                    board.pawn_matrix[y][x] = new Pawn(this,'w');
                else if((x == 4 && y == 3) || (x == 3 && y == 4))
                    board.pawn_matrix[y][x] = new Pawn(this,'b');
                else board.pawn_matrix[y][x] = new Pawn(this);


                board.pawn_matrix[y][x].getButton().getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                board.pawn_matrix[y][x].getButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),
                                "You clicked (" + row + "," + col + ")",
                                Toast.LENGTH_SHORT).show();
                        board.makeaMove(col,row);

                    }
                });
                r.addView(board.pawn_matrix[y][x].getButton());
            }
        }




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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
