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


    private Button addGoal;
    private Button addSaves;
    private Button addAssist;
    private Button addFoul;
    private Spinner selectTeam;
    private ArrayAdapter<String> adapterTeam;
    private Button nextPlayer;

    private TextView playerInfo;
    private ImageView playerPic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);


        selectTeam = (Spinner) findViewById(R.id.teamSelector);
        addGoal = (Button) findViewById(R.id.addGoals);
        addSaves = (Button) findViewById(R.id.addSaves);
        addAssist = (Button) findViewById(R.id.addAssists);
        addFoul = (Button) findViewById(R.id.addFoul);
        playerInfo = (TextView) findViewById(R.id.playerInfo);




        adapterTeam = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listOteams.toArray(new String[0]));
        adapterTeam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTeam.setAdapter(adapterTeam);







        selectTeam.setOnItemSelectedListener(new switchTeam());
    }

    private String getSelectedTeamName() {
        Object obj = selectTeam.getSelectedItem();
        return (String) obj;
    }


    public void goBack(View v)
    {

        finish();

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
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }


        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}
