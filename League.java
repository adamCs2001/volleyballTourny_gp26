
import java.util.*;
/**
 * The League class manages the whole league. from here you can add matches 
 * and check the standings in the league it keeps a list of all the teams 
 * currently in the league. It creates a league to be modified later
 *
 */
public class League
{
    // instance variables
    private ArrayList<Team> teamList;
    private int players;
    private int coaches;

    /**
     * Constructor for objects of class League
     */
    public League()
    {
        teamList = new ArrayList<>();
        final int players = 12;
        final int coaches = 2;

        leagueSetup();

    }

    /**
     * Simple method to create teams and adds them to divisions. 
     * Used in constructor to generate a league
     * 
     */
    private void leagueSetup()
    {
        for (int x = 0; x < 15; x++) {
            int division = ((x + 1) % 3) + 1;
            Team team = new Team("Team " + (x+1), division, players, coaches);
            teamList.add(team);
        }
    }

    /**
     * Iterate over the team list and put teams from the given division into a list then return it.
     *
     * @param  divisionName  name of the division
     * @return list of teams in the same division
     */
    public ArrayList<Team> getTeamsInDivision(String divisionName)
    {
        ArrayList<Team> divisionTeam = new ArrayList();
        for (Team team : teamList) 
        {
            if (team.getDivision().equals(divisionName))
            {
                divisionTeam.add(team);
            }
        }
        return divisionTeam;
    }

    /**
     * Score validator. used to validate score lists input by user to ensure that the they are valid uses the following checks:
     * 
     * size - both score list have to be the same length
     * 
     * min size - There has to be at least 3 sets played
     * 
     * max score - if any of the scores are over 21, the scores are rejected
     * 
     * min score - if any score is less than 0, the scores are rejected
     * 
     * declared winners - if there are less than 5 sets played, and less than 3 matches are won by either team, no winner is declared
     *                  - if there are 5 sets played, and the last score for either team isn't 15 no winner is declared
     */
    private boolean scoreValidator(ArrayList<Integer> scores1, ArrayList<Integer> scores2) {
        boolean err = true;

        if (scores1.size() != scores2.size()) {
            err = false;
            System.out.println("Different numbers of set scores");
        }

        if (scores1.size() < 2 || scores2.size() < 2) {
            err = false;
            System.out.println("Not enough games played");
        }

        if (scores1.stream().filter(x -> (x > 21)).filter(x -> (x < 0)).count() > 0) {
            err = false;
            System.out.println("Score 1 out of range");
        }

        if (scores2.stream().filter(x -> (x > 21)).filter(x -> (x < 0)).count() > 0) {
            err = false;
            System.out.println("Score 2 out of range");
        }

        if (scores1.size() < 5) {
            if (scores2.stream().filter(x -> (x == 21)).count() != 3 && scores1.stream().filter(x -> (x == 21)).count() != 3) {
                err = false;
                System.out.println("No winner declared");
            }
        } else {
            if (scores1.get(4) != 15 && scores2.get(4) != 15) {
                err = false;
                System.out.println("No winner declared");
            }
        }

        return err;
    }

    /**
     * Prints out a list of all the teams for all 3 divisions, helpful for adding matches.
     */
    public void getDivisionTeams() {
        System.out.println("Division 1 Teams");
        teamList.stream().filter(team -> team.getDivision().equals("Division 1")).forEach(team -> System.out.println(team.getName()));

        System.out.println("Division 2 Teams");
        teamList.stream().filter(team -> team.getDivision().equals("Division 2")).forEach(team -> System.out.println(team.getName()));

        System.out.println("Division 3 Teams");
        teamList.stream().filter(team -> team.getDivision().equals("Division 3")).forEach(team -> System.out.println(team.getName()));
    }

    /**
     * Add a match and all relevant scores to the teams participating
     * Checks if both teams are in the same division, if they aren't, does nothing
     *
     * 
     */
    public void addMatch(String team1, String team2, 
    String score1, String score2)
    {
        ArrayList<Integer> scoreOne = new ArrayList<>();
        ArrayList<Integer> scoreTwo = new ArrayList<>();
        Team teamOne = teamList.stream().filter(team -> team.getName().equals(team1)).findFirst().get();
        Team teamTwo = teamList.stream().filter(team -> team.getName().equals(team2)).findFirst().get();

        Arrays.asList(score1.split(",")).forEach(num -> scoreOne.add(Integer.parseInt(num.trim())));
        Arrays.asList(score2.split(",")).forEach(num -> scoreTwo.add(Integer.parseInt(num.trim())));
        if (!(teamOne.getDivision().equals(teamTwo.getDivision()))) {
            System.out.println("Teams not in same division.");
            return;
        }

        if (!scoreValidator(scoreOne, scoreTwo)) {
            System.out.println("Scores entered incorrectly.");
            return;
        } 

        int point1 = 0;
        int point2 = 0;

        for (int x = 0; x < scoreOne.size(); x++)
        {
            int pointDiff = scoreOne.get(x) - scoreTwo.get(x);
            if (pointDiff > 0)
            {
                teamOne.updateLeaguePoints();
                point1++;
            }
            else
            {
                teamTwo.updateLeaguePoints();
                point2++;
            }

            teamOne.updatePointDiff(pointDiff);
            teamTwo.updatePointDiff(-pointDiff);

            if ((point1 == 3) || (point2 == 3))
            {
                break;
            }
        }

        if (point1 == 3)
        {
            teamOne.updateLeaguePoints();
        }
        else 
        {
            teamTwo.updateLeaguePoints();
        }

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
     * divisionName will always be "Division 1, Division 2, or Division 3"
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void calculateStanding(String divisionName)
    {
        ArrayList<Team> List = getTeamsInDivision(divisionName);

        Collections.sort(List, Comparator.comparing(Team::getLeaguePoints)
            .thenComparing(Team::getpointDiff));
        Collections.reverse(List);

        int rank = 1;
        for(Team team : List) 
        {
            System.out.println("Rank: " + rank + "," + "Team name: " + team.getName() + ", " + "Points: " + team.getLeaguePoints() + ", " + "Point sum: " + team.getpointDiff() );
            rank++;
        }
    }

}
