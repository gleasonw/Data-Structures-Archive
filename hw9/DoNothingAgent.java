/**
 * DoNothingAgent.java
 * Titus Klinge 11 Nov 2018
 */

import edu.iastate.cs228.game.*;
import static edu.iastate.cs228.game.Agent.*;

import java.awt.Color;
import java.io.File;
import java.util.Random;

public class CoolGuy implements Agent
{
    /********************************************************************************
     * YOU DO NOT NEED TO CHANGE ANY OF THE CODE IN THIS BOX                        *
     ********************************************************************************/
    public String getStuID() { return ""; }
    public String getUsername() {return ""; }
    public void setColor(Color paramColor) { this.myColor = paramColor; }
    public void setOpponentColor(Color paramColor) { this.theirColor = paramColor; }
    /********************************************************************************/

    private Color myColor;      // Your agent color
    private Color theirColor;   // Your opponent's agent color
  
    public File getAgentImage()
    {
        // If you want your agent to have a custom image, load it here and
        // include it with your submission
        return null;
    }

    public String getFirstName()
    {
        // Return your first name here
        return "";
    }

    public String getLastName()
    {
        // Return your last name here
        return "";
    }

    public String getAgentName()
    {
        // Give your agent an interesting name
        return "Do Nothing";
    }

    public boolean inTournament()
    {
        // Change this to return true if you want to participate in the
        // in class tournament
        return false;
    }
  
    // Method that will be used for grading purposes
    public AgentAction[] getCommandTurnGrading(GalaxyState galaxy, int energy)
    {
        return getCommandTurnTournament(galaxy, energy);
    }
  
    // Method that will be used for the in-class tournament
    public AgentAction[] getCommandTurnTournament(GalaxyState galaxy, int energy)
    {
        AgentAction[] actions = new AgentAction[3];
        actions[0] = new NoAction();
        actions[1] = new NoAction();
        actions[2] = new NoAction();
        return actions;
    }
}
