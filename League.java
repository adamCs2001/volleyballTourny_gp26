
    import java.util.*;
    /**
     * The League class manages the whole league. from here you can add matches 
     * and check the standings in the league it keeps a list of all the teams 
     * currently in the league
     *
     */
    public class League
    {
        // instance variables
        private ArrayList<Team> teamList;
        private int players;
        private int coaches;
        private ArrayList<Integer> score1;
        private ArrayList<Integer> score2;
    
        /**
         * Constructor for objects of class League
         */
        public League()
        {
            teamList = new ArrayList<>();
            final int players = 12;
            final int coaches = 2;
            
        }
        
        /**
         * Simple method to create teams and adds them to divisions.
         * 
         */
        public void leagueSetup()
        {
            for (int x = 0; x < 15; x++) {
                int division = ((x + 1) % 3) + 1;
                Team team = new Team("Team " + (x+1), division, players, coaches);
                teamList.add(team);
            }
        }
        
        /**
         * Simple method to check if both teams are in the same division should return true or false
         *
         * @param  y  a sample parameter for a method
         * @return    boolean
         */
        private boolean checkDivision(Team team1, Team team2)
        {
            if (team1.getDivision().equals(team2.getDivision()))
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        
        
        /**
         * Iterate over the team list and put teams from the given division into a list then return it.
         *
         * @param  divisionName  name of the division
         * @return list of teams in the same division
         */
        private ArrayList<Team> getTeamsInDivision(String divisionName)
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
         * Starts a tournament based on the League setup, creates matches between teams in the same division.
         *
         */
        public void tournament()
        {
            Random rand = new Random();
            for (int x = 1; x < 4; x++)
            {
                ArrayList<Team> divisionTeam = getTeamsInDivision("Division " + x);
                
                while (divisionTeam.size() != 1)
                {
                    
                    for (int i = 1; i < divisionTeam.size(); i++)
                    {
                        ArrayList<ArrayList<Integer>> scoreList = createScore();
                        System.out.println(divisionTeam.get(0).getName() + " : " + divisionTeam.get(i).getName());
                        System.out.println(scoreList); // I put this and the line above to check the content of scoreList, remove if you want.
                        addMatch(divisionTeam.get(0),divisionTeam.get(i),scoreList.get(0),scoreList.get(1));
                        
                    }
                    divisionTeam.remove(0);
                }
            }
        }
        
        /**
         * Generate two lists of scores for two teams.
         *
         */
        private ArrayList<ArrayList<Integer>> createScore()
        {
            score1 = new ArrayList();
            score2 = new ArrayList();
            int point1 = 0;
            int point2 = 0;
            int maxScore = 21;
            Random rand = new Random();
                for (int i = 0; i < 5; i++)
                {
                    // If reaches 5th match.
                    if (i == 4) 
                    {
                        maxScore = 15;
                    }
                    
                    if (rand.nextInt(2) == 0)
                    {
                        score1.add(maxScore);
                        score2.add(rand.nextInt(maxScore));
                        point1++;
                    }
                    else 
                    {
                        score2.add(maxScore);
                        score1.add(rand.nextInt(maxScore));
                        point2++;
                    }
                    // If one of the teams reaches 3 points.
                    if (point1 == 3 || point2 == 3) 
                    {
                        break;
                    }
                    
                }
            ArrayList<ArrayList<Integer>> arr = new ArrayList();
            arr.add(score1);
            arr.add(score2);
            return arr;
        }
    
    
        /**
         * Add a match and all relevant scores to the teams participating
         * Checks if both teams are in the same division, if they aren't, does nothing
         *
         * 
         */
        private void addMatch(Team team1, Team team2, 
                             ArrayList<Integer> score1, ArrayList<Integer> score2)
        {
            if (!checkDivision(team1,team2))
            {
                ;
            }
            
            int point1 = 0;
            int point2 = 0;
            
            for (int x = 0; x < score1.size(); x++)
             {
                int pointDiff = score1.get(x) - score2.get(x);
                if (pointDiff > 0)
                {
                    team1.updateLeaguePoints();
                    point1++;
                }
                else
                {
                    team2.updateLeaguePoints();
                    point2++;
                }
                
                team1.updatePointDiff(pointDiff);
                team2.updatePointDiff(-pointDiff);
                
                if ((point1 == 3) || (point2 == 3))
                {
                    break;
                }
            }
            
            if (point1 == 3)
            {
                team1.updateLeaguePoints();
            }
            else 
            {
                team2.updateLeaguePoints();
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
         * @param  y  a sample parameter for a method
         * @return    the sum of x and y
         */
        public void calculateStanding(String divisionName)
        {
            Map<Integer, String> pointList = new TreeMap<>((Comparator<Integer>) (o1, o2) -> o2.compareTo(o1));
            ArrayList<Team> List = getTeamsInDivision(divisionName);
        
            for (Team team : List)
            {
                pointList.put(team.getLeaguePoints(),team.getName());
            }
            int rank = 1;
            for(Map.Entry <Integer, String> entry : pointList.entrySet()) 
            {
                System.out.println("Team name: " + entry.getValue() + ", " + "Points: " + entry.getKey() + ", " + "Rank: " + rank);
                rank++;
            }
        
        }
        
}
