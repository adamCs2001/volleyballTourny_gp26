------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Volleyball Tournament
PURPOSE OF PROJECT: Assignment 4
VERSION or DATE:
HOW TO START THIS PROJECT:
AUTHORS: Adam Lichter, Nhu Nguyen, Emily Seward
USER INSTRUCTIONS:

Two methods of using this program

1: Instantiating the league class manually. it runs the league setup and generates a league of 15 teams in 3 divisions. 
getDivisionTeams() will print out a list of teams in each division. This is to make sure that when adding matches, teams 
from the same division are added.

once a league class has been created, a user can use the method "addMatch" on the league class. the user then needs to 
add team names for the teams that are facing each other, as well as a comma separated list of scores to represent each 
set played. this will be stored on each team that played to be used for calculating standings.

After every match added, it's possible to check the standings of the teams. the user adds in a division by entering a 
string "Division" plus 1, 2, or 3 (since there are only 3 divisions). it generates a list of teams by rank in decending order.


2: Using the Tournament Simulation. After a league is created, the tournament sim can be used to simulate each team from 
every division playing each other team in the division. calculating the standing on the league will show the results of the 
tournament 