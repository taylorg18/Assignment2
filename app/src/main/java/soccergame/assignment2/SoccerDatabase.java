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

    public SoccerDatabase() {
        Hashtable<String, soccerPlayer> playerDB = new Hashtable<String, soccerPlayer>();
        this.playerDB=playerDB;
        Hashtable<String, team> teamDB = new Hashtable<String, team>();
        this.teamDB=teamDB;
    }

    public void addPlayer(String name, int uniformNumber, String teamName, String position)
    {
        soccerPlayer Player = new soccerPlayer(name,teamName, uniformNumber, position);
        playerDB.put(name, Player);
    }

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
     * look up a player
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

    /**
     * increment a player's goals
     * */

    public void bumpGoals(String name)
    {
        playerDB.get(name).setGoals();
    }

    /**
     * increment a player's assists
     */

    public void bumpAssists(String name)
    {
        playerDB.get(name).getAssists();
    }


    /**
     * increment a player's saves
     *
     */

    public void bumpSaves(String name)
    {
        playerDB.get(name).getSaves();
    }

    /**
     * increment a player's fouls
     *
     */

    public void bumpFouls(String name)
    {
        playerDB.get(name).getFouls();
    }


    /**
     * tells the number of players on a given team
     *
*/
    public int numPlayers(String teamName) {
        return -1;
    }

    /**
     * gives the nth player on a the given team
    */

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
