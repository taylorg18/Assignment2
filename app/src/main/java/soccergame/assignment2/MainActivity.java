package soccergame.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity
{

    private EditText playerName;
    private EditText uniformNumb;
    private EditText teamName;
    private EditText teamLocal;
    private Button addPlayer;
    private Button addTeam;
    private Spinner selectTeam;
    private ExpandableListView listAll;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listOteams;
    private SoccerDatabase data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create components on activity
        playerName = (EditText) findViewById(R.id.playerName);
        uniformNumb = (EditText) findViewById(R.id.playerNumber);
        teamName = (EditText) findViewById(R.id.team);
        teamLocal = (EditText) findViewById(R.id.teamLocation);
        selectTeam = (Spinner) findViewById(R.id.teamSelector);
        addPlayer = (Button) findViewById(R.id.addPlayer);
        addTeam = (Button) findViewById(R.id.addTeam);
        listAll = (ExpandableListView) findViewById(R.id.displayList);
        listOteams = new ArrayList<String>();
        listOteams.add("Hello");
        listOteams.add("Grayson");
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listOteams.toArray(new String[0]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTeam.setAdapter(adapter);
        data = new SoccerDatabase();
       // addPlayer.setOnClickListener();
       // addTeam.setOnClickListener();
    }

    private String getSelectedTeamName() {
        Object obj = selectTeam.getSelectedItem();
        return (String) obj;
    }


    private void addTeam(String name) {
        // if player is already there, leave things alone
        if (listOteams.indexOf(name) >= 0) return;

        // add the name; resort
        listOteams.add(name);
        Collections.sort(listOteams);

        // create new adapter, add the updated elements and connect to the spinner
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                        android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTeam.setAdapter(spinnerAdapter);
        spinnerAdapter.addAll(listOteams);
        spinnerAdapter.notifyDataSetChanged();
    }

    private void update(boolean reLoad)
    {
        if(reLoad) {
            playerName.setText("");
            teamName.setText("");
            uniformNumb.setText("");
            teamLocal.setText("");
        }

    }
    private int getUniformNumber() {
        try {
            return Integer.parseInt(uniformNumb.getText().toString().trim());
        } catch (NumberFormatException nfx) {
            return -100;
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
