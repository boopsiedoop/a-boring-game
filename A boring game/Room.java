import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "A boring game" application. 
 * "A boring game" is a very boring text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Daniëlle van der Tuin
 * @version 23-1-2020
 */

public class Room 
{
    public boolean access;
    private String description;
    private HashMap<String, Room> exits; 
    public String name;
    // stores exits of this room.

    /**
     * Create a room. Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     * @param description The room's description.
     * @param access is whether the room is accesible.
     * @param name is the name of the room.
     */
    public Room(String description, boolean access, String name) 
    {
        this.description = description;
        this.access = access;// ik heb een access boolean gemaakt zodat je pas in een kamer kan als je in goRoom voldoen aan bepaalde voorwaarden
        this.name = name;// ik heb een name variabel gemaakt zodat ik in goRoom kan vragen wat de currentroom
        exits = new HashMap<>();
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Directions: north west".
     * @return Details of the room's exits.
     */
    
    private String getExitString()
    {
        String returnString = "Directions:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit + "" ;
        }
        if (returnString == "Directions:" ){
           returnString = ""; // als er geen kant is waar je heen kan dan is het niet nodig om Directions: neer te zetten dat staat alleen maar lelijk
        }
        return returnString;
    }
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

