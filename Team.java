import java.util.ArrayList;
import java.lang.Math;

/**
 * Write a description of class Team here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Team
{
    // instance variables - replace the example below with your own
    private String name;
    private String division;
    private ArrayList<Player> playerList;
    private ArrayList<Coach> coachList;
    private int leaguePoints;
    private int pointDiff;

    /**
     * Constructor for objects of class Team
     * 
     * take in all the informationto create a team. keeps a list of all the players 
     * and coaches associated with the team, currently only stores their names in a 
     * list
     * 
     * Team keeps track of it's current league points as well as the running tally of 
     * the point diff for each of the games that it's taken part of for standing purposes. 
     * 
     * 
     * @param name a name String for the team
     * @param division an int to represent the division
     * @param players an int to indicate the number of players that should be on a team
     * @param coaches an int to indicate the number of coaches that should be on a team
     */
    public Team(String name, int division, int players, int coaches)
    {
        this.name = name;
        this.division = "Division " + division;
        this.playerList = new ArrayList<>();
        this.leaguePoints = 0;
        this.pointDiff = 0;

        for (int x = 0; x < players; x++) {
            int jerseyNum = (int)Math.random() * 100 + 1;
            Player player = new Player("player " + jerseyNum);

            playerList.add(player);
        }

        for (int x = 0; x < coaches; x++) {
            int jerseyNum = (int)Math.random() * 100 + 1;
            Coach coach = new Coach("Coach " + jerseyNum);

            coachList.add(coach);
        }

    }
    
    /**
     * returns the division associated with this team
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * returns the division associated with this team
     */
    public String getDivision()
    {
        return this.division;
    }

    /**
     * returns the number of league points won by this team
     */
    public int getLeaguePoints()
    {
        return this.leaguePoints;
    }

    /**
     * returns the delta of points won/lost by this team
     */
    public int getpointDiff()
    {
        return this.pointDiff;
    }

    /**
     * updates the delta of points won/lost by this team
     */
    public void updatePointDiff(int addPoints)
    {
        this.pointDiff += addPoints;
    }

    /**
     * updates the league points won by this team
     */
    public void updateLeaguePoints()
    {
        this.leaguePoints++;
    }
}
