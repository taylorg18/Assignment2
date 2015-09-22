package soccergame.assignment2;

/**
 * Created by Grayson on 9/20/2015.
 */
public class team
{

    String teamName;
    String location;
    int wins;
    int losses;


    public team(String inname, String inlocation)
    {
        teamName = inname;
        location = inlocation;
        wins = 0;
        losses =0;
    }
    //increases win
    public void winner()
    {
        wins++;
    }
    //returns wins
    public int getWins()
    {
        return wins;
    }
    //increases loss
    public void loser()
    {
        losses++;
    }
    //returns losses
    public int getLosses()
    {
        return losses;
    }
    //returns the team name
    public String getTeamName()
    {
        return teamName;
    }
    //sets the team name
    public void setTeamName(String inteam)
    {
        this.teamName = inteam;
    }
    //returns location
    public String getLocation()
    {
        return location;
    }
    //sets the location
    public void setLocation(String inlocation)
    {
        this.location = inlocation;
    }
}
