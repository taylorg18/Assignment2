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
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {

    private EditText playerName;
    private EditText uniformNumb;
    private EditText teamName;
    private EditText teamLocal;
    private EditText playPostion;
    private Button addPlayer;
    private Button addTeam;
    private Spinner selectTeam;
    private ArrayAdapter<String> adapter;
    public ArrayList<String> listOteams;
    public SoccerDatabase data;
    private TextView teamInfo;
    private ImageView teamPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create components on activity
        playerName = (EditText) findViewById(R.id.playerName);
        uniformNumb = (EditText) findViewById(R.id.playerNumber);
        teamName = (EditText) findViewById(R.id.team);
        teamLocal = (EditText) findViewById(R.id.teamLocation);
        playPostion = (EditText) findViewById(R.id.playerPosition);
        selectTeam = (Spinner) findViewById(R.id.teamSelector);
        addPlayer = (Button) findViewById(R.id.addPlayer);
        addTeam = (Button) findViewById(R.id.addTeam);
        teamInfo = (TextView) findViewById(R.id.teamDisplay);
        data = new SoccerDatabase();

        data.addTeam("Gotham Knights", "Gotham");
        data.addTeam("Impalas", "Kansas");
        data.addPlayer("Bruce Wayne", 60, data.getTeamName("Gotham Knights"), "Forward");
        data.addPlayer("Dick Grayson", 25, data.getTeamName("Gotham Knights"), "Defender");
        data.addPlayer("Dean Winchester", 1, data.getTeamName("Impalas"), "Forward");
        data.addPlayer("Sam Winchester", 2, data.getTeamName("Impalas"), "Defender");





        listOteams = new ArrayList<String>();
        listOteams.add(data.getTeamName("Gotham Knights"));
        listOteams.add(data.getTeamName("Impalas"));
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listOteams.toArray(new String[0]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTeam.setAdapter(adapter);

        addPlayer.setOnClickListener(new addPlayerListener());
        selectTeam.setOnItemSelectedListener(new teamChange());
        update(true);
    }


    private String getSelectedTeamName() {
        Object obj = selectTeam.getSelectedItem();
        return (String) obj;
    }


    public void addTeam(View v) {

        String name = teamName.getText().toString();
        String Location = teamLocal.getText().toString();
        if (listOteams.indexOf(name) >= 0) return;

        // add the name;
        data.addTeam(name, Location);
        listOteams.add(data.getTeamName(name));
        Collections.sort(listOteams);

        // create new adapter, add the updated elements and connect to the spinner
       /* ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                        android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTeam.setAdapter(spinnerAdapter);*/
        adapter.addAll(listOteams);
        adapter.notifyDataSetChanged();
        update(true);
    }


    private void update(boolean reLoad) {
        if (reLoad) {
            playerName.setText("");
            teamName.setText("");
            uniformNumb.setText("");
            teamLocal.setText("");
            playPostion.setText("");
        }



    }

    private int getUniformNumber() {
        try {
            return Integer.parseInt(uniformNumb.getText().toString().trim());
        } catch (NumberFormatException nfx) {
            return -100;
        }
    }

    public void viewPlayers(View v)
    {
        Intent switchActivity = new Intent(this, playerList.class);
        startActivity(switchActivity);


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


    public class addPlayerListener  implements View.OnClickListener {
        /**
         * respond to the press of the "New player" button
         *
         * @see android.view.View.OnClickListener#onClick(android.view.View)
         */
        @Override
        public void onClick(View view) {
            // get the data from the relevant text fields
            String name = playerName.getText().toString();
            int uniformNumber = getUniformNumber();
            String position = playPostion.getText().toString();

            // if any fields were empty or otherwise incorrect, flash and return
            if (name.isEmpty() || uniformNumber < 0) {

                update(true);
                return;
            }

            // attempt to add the player; if unsuccessful, flash and return
            data.addPlayer(name, uniformNumber, getSelectedTeamName(), position);

                update(true);



        }
    }

    public class teamChange implements AdapterView.OnItemSelectedListener
    {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String teamName = getSelectedTeamName();
            String Location = data.getTeam(teamName).getLocation();
            int wins = data.getTeam(teamName).getWins();
            int loss = data.getTeam(teamName).getLosses();

            teamInfo.setText(teamName + "\n" + Location + "\nWins: " + wins + "\nLosses: " + loss);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}


