import java.util.*;
/**
 * Write a description of class TournamentSim here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TournamentSim
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class TournamentSim
     */
    public TournamentSim()
    {
    }

    /**
    * Generate two lists of scores for two teams.
    *
    */
    public ArrayList<String> createScore()
    {
          int point1 = 0;
          int point2 = 0;
          int maxScore = 21;
          Random rand = new Random();
          String score1 = new String();
          String score2 = new String();
          for (int i = 0; i < 5; i++)
          {
                  // If reaches 5th match.
                  if (i == 4)
                  {
                        maxScore = 15;
                  }
                  
                  if (rand.nextInt(2) == 0)
                  {
                      score1 = score1.concat(Integer.toString(maxScore) + ",");
                      score2 = score2.concat(Integer.toString(rand.nextInt(maxScore)) + ",");
                      point1++;
                  }
                  else 
                  {
                      score2 = score2.concat(Integer.toString(maxScore) + ",");
                      score1 = score1.concat(Integer.toString(rand.nextInt(maxScore)) + ",");
                      point2++;
                  }
                  // If one of the teams reaches 3 points.
                  if (point1 == 3 || point2 == 3) 
                  {
                      break;
                  }
                    
          }
          
          ArrayList<String> arr = new ArrayList();
          arr.add(score1);
          arr.add(score2);
          return arr;
    }
        
    /**
         * Starts a tournament based on the League setup, creates matches between teams in the same division.
         *
         */
    public void tournament(League league)
    {
         Random rand = new Random();
         for (int x = 1; x < 4; x++)
         {
             System.out.println("Division " + x);
             ArrayList<Team> divisionTeam = league.getTeamsInDivision("Division " + x);
             
             while (divisionTeam.size() != 1)
             {
                    
                 for (int i = 1; i < divisionTeam.size(); i++)
                 {
                     ArrayList<String> scoreList = createScore();
                     System.out.println(divisionTeam.get(0).getName() + " : " + divisionTeam.get(i).getName());
                     System.out.println(scoreList); // I put this and the line above to check the content of scoreList, remove if you want.
                     league.addMatch(divisionTeam.get(0).getName(),divisionTeam.get(i).getName(),scoreList.get(0),scoreList.get(1));
                 }
                 divisionTeam.remove(0);
             }
         }
    }
}
