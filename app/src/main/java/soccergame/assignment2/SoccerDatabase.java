package soccergame.assignment2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Created by Grayson on 9/21/2015.
 */






public class SoccerDatabase implements Serializable {
    Hashtable<String, soccerPlayer> playerDB;
    Hashtable<String, team> teamDB;

    //uses hashtables to store the teams and players
    public SoccerDatabase() {
        Hashtable<String, soccerPlayer> playerDB = new Hashtable<String, soccerPlayer>();
        this.playerDB=playerDB;
        Hashtable<String, team> teamDB = new Hashtable<String, team>();
        this.teamDB=teamDB;
    }
    //adds a player to the given hashtable
    public void addPlayer(String name, int uniformNumber, String teamName, String position)
    {
        soccerPlayer Player = new soccerPlayer(name,teamName, uniformNumber, position);
        playerDB.put(name, Player);
    }
    //adds a team to the hashtable
    public void addTeam(String name, String Local)
    {
        team newTeam = new team(name,Local);
        teamDB.put(name, newTeam);
    }

    /**
     * remove a player
     */

    public void removePlayer(String name)
    {

      playerDB.remove(name);
    }

    /**
     * look up a player,team,or team name
     *
     */
    public soccerPlayer getPlayer(String name)
    {
        return playerDB.get(name);
    }

    public team getTeam(String name)
    {
        return teamDB.get(name);
    }

    public String getTeamName(String name)
    {
        return teamDB.get(name).teamName;
    }


    //finds the players on the team
    public soccerPlayer playerNum(int idx, String teamName)
    {
        Collection<String> theTeam = Collections.list(playerDB.keys());
        soccerPlayer[] players = new soccerPlayer[20];
        int i=0;
        for( String s : theTeam)
        {
            if (playerDB.get(s).getTeam().equalsIgnoreCase(teamName))
            {
                players[i] = playerDB.get(s);
                        i++;
            }


        }


        return players[idx];
    }



}
