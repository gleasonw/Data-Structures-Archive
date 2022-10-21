/**
 * DoNothingAgent.java
 * Titus Klinge 11 Nov 2018
 * Will Gleason and Matt Stecklow 11/15/2018
 */

import edu.iastate.cs228.game.*;
import static edu.iastate.cs228.game.Agent.*;

import java.awt.Color;
import java.io.File;
import java.util.*;

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
    private Color theirColor; // Your opponent's agent color
    private Stack<SystemState> travelStack = new Stack<SystemState>();
  
    public File getAgentImage()
    {
        // If you want your agent to have a custom image, load it here and
        // include it with your submission
        return null;
    }

    public String getFirstName()
    {
        // Return your first name here
        return "Will, Matt";
    }

    public String getLastName()
    {
        // Return your last name here
        return "Gleason, Stecklow";
    }

    public String getAgentName()
    {
        // Give your agent an interesting name
        return "Morgan Freeman";
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
    
    private int getTakeoverCost(SystemState attackGalaxy, String homeGalaxy) {
        for (int i = 0; i < attackGalaxy.getNeighbors().length(); i++) {
            if attackGalaxy.getNeighbors[i].getName().equals(homeGalaxy) {
                int travelCost = attackGalaxy.getTravelCost[i];
            }
        }
        return travelCost + attackGalaxy.getCostToCapture();
    }
  
    // Method that will be used for the in-class tournament
    public AgentAction[] getCommandTurnTournament(GalaxyState galaxy, int energy)
    {
        AgentAction[] actions = new AgentAction[3];
        SystemState currSystem = galaxy.getCurrentSystemFor(this.myColor);
        SystemState[] scannedSystems = galaxy.getSystems();
        String currSystemName = currSystem.getName();
        energy++;
        
         for(int i = 1; i < scannedSystems.length(); i++){
            SystemState currState = scannedSystems[i];
            int j = i - 1;
            while(j >= 0 && getTakeoverCost(currState, currSystemName) >  getTakeoverCost(scannedSystems[j], currSystemName)) {
                SystemState temp = scannedSystems[j];
                scannedSystems[j] = scannedSystems[j+1];
                scannedSystems[j+1] = temp;
                j--;
            }
        }
        
        for(SystemState k : scannedSystems) {
            if (k.getOwner() != myColor && travelStack.search(k) == -1) {
                travelStack.push(k);
            }
        }
        
        if (currSystem.getOwner() != this.myColor) {
            if (currSystem.getCostToCapture() <= energy) {
                actions[0] = new Capture(currSystem.getCostToCapture() - 1);
                energy = energy - currSystem.getCostToCapture();
                energy++;
            } else {
                actions[0] = new Move(travelStack.pop().getName());
                travelStack.push(currSystem);
            }
        } else {
            actions[0] = new Refuel();
            energy += (currSystem.getEnergyStored() + 1);
            if (getTakeoverCost(travelStack.peek(), currSystemName) > energy) {
                actions[1] = new NoAction();
                actions[2] = new NoAction();
            } else {
                SystemState getThisGalaxy = travelStack.pop();
                actions[1] = new Move(getThisGalaxy.getName());
                actions[2] = new Capture(getThisGalaxy.getCostToCapture() - 1);
                travelStack.push(currSystem);
            }         
        }
        return actions;
    }
}
