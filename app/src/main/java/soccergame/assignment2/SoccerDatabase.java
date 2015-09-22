package soccergame.assignment2;
import java.util.Hashtable;


/**
 * Created by Grayson on 9/21/2015.
 */






public class SoccerDatabase {

    Hashtable<String, soccerPlayer> playerDB = new Hashtable<String,soccerPlayer>();
    Hashtable<String, team> teamDB = new Hashtable<String, team>();


    public void addPlayer(String name, int uniformNumber, String teamName, String position)
    {
        soccerPlayer Player = new soccerPlayer(name,teamName, uniformNumber, position);
        playerDB.put(name, Player);
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

    public soccerPlayer playerNum(int idx, String teamName) {
        return null;
    }



}
