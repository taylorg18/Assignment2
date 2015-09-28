package soccergame.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class playerList extends MainActivity {

    //setup the views on the activity
    private Button addGoal;
    private Button addSaves;
    private Button addAssist;
    private Button addFoul;
    private Spinner switchTeam;
    private Button nextPlayer;

    private TextView playerInfo;
    private ImageView playerPic;

    private ArrayAdapter<String> Teamadapter;
    private ArrayList<String> TEAMLIST;
    private int playerNum=-1;
    private soccerPlayer currentPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        //find the views
        switchTeam = (Spinner) findViewById(R.id.teamSelector);
        addGoal = (Button) findViewById(R.id.addGoals);
        addSaves = (Button) findViewById(R.id.addSaves);
        addAssist = (Button) findViewById(R.id.addAssists);
        addFoul = (Button) findViewById(R.id.addFoul);
        playerInfo = (TextView) findViewById(R.id.playerInfo);
        playerPic = (ImageView) findViewById(R.id.playerPic);
        //get the team list fromt the last activity
        Bundle list = this.getIntent().getExtras();
        TEAMLIST = list.getStringArrayList("TEAMS");

        Teamadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, TEAMLIST.toArray(new String[0]));
        Teamadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        switchTeam.setAdapter(Teamadapter);


        switchTeam.setOnItemSelectedListener(new switchTeam());

    }
    //gets the team from the spinner
    private String getSelectedTeamName() {
        Object obj = switchTeam.getSelectedItem();
        return (String) obj;
    }

    //returns to the last activity
    public void goBack(View v)
    {

        finish();

    }

    private String getSelectedteam() {
        Object obj = switchTeam.getSelectedItem();
        return (String) obj;
    }

    //displays the next player on the team
    public void nextPlayer(View v)
    {
        playerNum++;
        currentPlayer = data.playerNum(playerNum, getSelectedteam());
        if(currentPlayer == null)
        {
            currentPlayer = data.playerNum(0,getSelectedteam());
            playerNum=0;
            if(currentPlayer == null)
            {
                return;
            }
        }
        displayPlayer(currentPlayer);
    }

    //displays the players info and selects a picture
    public void displayPlayer(soccerPlayer next)
    {
        String name = next.getName();
        String team = next.getTeam();
        String pos = next.getPosition();
        int num = next.getUniform();
        int goals = next.getGoals();
        int saves = next.getSaves();
        int assists = next.getAssists();
        int fouls = next.getFouls();

        playerInfo.setText(name + "\n" + num + "\n" + team + "\n" + pos + "\n"
                + "Goals: " + goals + "\nSaves: " + saves
                + "\nAssists: " + assists + "\nFouls: " + fouls);
        //player picture is based on name length
        if(name.length() < 10)
        {
            playerPic.setImageResource(R.drawable.drew);
        }
        else if (name.length() < 15)
        {
            playerPic.setImageResource(R.drawable.clubfair);
        }
        else if (name.length() < 20)
        {
            playerPic.setImageResource(R.drawable.lady);
        }
        else if (name.length() < 30)
        {
            playerPic.setImageResource(R.drawable.jav);
        }
        else
        {
            playerPic.setImageResource(R.drawable.drew);
        }


    }

    //increments the corresponding stat to the button
    public void addStats(View v)
    {
       if (v == addGoal)
       {
           currentPlayer.setGoals();
       }
       else if (v == addSaves)
        {
            currentPlayer.setSaves();
        }
        else if (v == addAssist)
        {
            currentPlayer.setAssists();
        }
        else if (v == addFoul)
        {
            currentPlayer.setFouls();
        }
        displayPlayer(currentPlayer);
    }

    //takes the user to the soccer field activity
    public void playGame(View v)
    {
        Intent switchActivity = new Intent(this, soccerField.class);
        switchActivity.putExtra("yourTeam",getSelectedTeamName());
        startActivity(switchActivity);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_list, menu);
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

    public class switchTeam implements AdapterView.OnItemSelectedListener
    {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {

        }


        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}
