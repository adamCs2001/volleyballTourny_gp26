import java.util.ArrayList;
/**
 * The League class manages the whole league. from here you can add matches 
 * and check the standings in the league it keeps a list of all the teams 
 * currently in the league
 *
 */
public class League
{
    // instance variables - replace the example below with your own
    ArrayList<Team> teamList;

    /**
     * Constructor for objects of class League
     */
    public League()
    {
        
        this.teamList = new ArrayList<>();
        final int players = 12;
        final int coaches = 2;
        
        for (int x = 0; x < 15; x++) {
            int division = ((x+1)%3) + 1;
            Team team = new Team("Team " + (x+1), division, players, coaches);
            teamList.add(team);
        }
    }
    
        /**
     * simple method to check if both teams are in the same division should return true or false
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    private boolean checkDivision(Team team1, Team team2)
    {
        // put your code here
        return true;
        
    }

    /**
     * add a match and all relevant scores to the teams participating
     * checks if both teams are in the same division, if they aren't, does nothing
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void addMatch(Team team1, Team team2)
    {
        // put your code here
        
    }
    
        /**
     * iterates over a division and prints out the standings. should print 
     * them in order from 1st to last place.
     * 
     * has to adjust standing by both league points and point diff which is 
     * stored on each team. could do something here where if a team has 0 
     * points and 0 ptdiff (because they didn't compete or something) make 
     * it print them last with DNQ (did not qualify)
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void calculateStanding(String divisionName)
    {
        // put your code here
        
    }
    
}
