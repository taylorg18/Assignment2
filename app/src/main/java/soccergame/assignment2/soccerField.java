package soccergame.assignment2;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;


public class soccerField extends MainActivity implements View.OnTouchListener{

    //set up the activity
    private Canvas c;
    fieldSurface field;
    private TextView team1;
    private TextView team2;
    private TextView winDisplay;
    String yourteam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer_field);
        //find all the views
        field = (fieldSurface) findViewById(R.id.field);
        team1 = (TextView) findViewById(R.id.team1);
        team2 = (TextView) findViewById(R.id.team2);
        winDisplay = (TextView) findViewById(R.id.winner);
        Bundle list = this.getIntent().getExtras();
         yourteam = (String) list.get("yourTeam");
        team1.setText(yourteam);
        //select the opponent randomly
        double selectopp = (Math.random()*2);
        if(selectopp < 1) {
            team2.setText(data.getTeam("Impalas").teamName);
        }
        else
        {
            team2.setText(data.getTeam("Gotham Knights").teamName);
        }
        //allows the user to move the view around after they win
        winDisplay.setOnTouchListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_soccer_field, menu);
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

    //allows the textview to be dragged around
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        winDisplay.setX(event.getX());
        winDisplay.setY(event.getY());
        return true;
    }
    //returns to previous activity
    public void quitGame(View v)
    {
        finish();
    }

    //plays the game
    public void kickOff(View v)
    {
        //randomly select winner, displays their name, and increments their stats
        double selectWin = (Math.random()*2);
        if(selectWin < 1) {
            winDisplay.setText("Winner: " + yourteam);
            winDisplay.setVisibility(View.VISIBLE);
            if(data.getTeam(yourteam) != null) {
                data.getTeam(yourteam).winner();
            }

            data.getTeam((String) team2.getText()).loser();


    } else
        {
            winDisplay.setText("Winner: " + team2.getText());
            winDisplay.setVisibility(View.VISIBLE);
            data.getTeam((String) team2.getText()).winner();
            if(data.getTeam(yourteam) != null) {
                data.getTeam(yourteam).loser();
            }
        }
    }
}
