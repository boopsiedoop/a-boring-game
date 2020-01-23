/**
 * This class is part of the "A boring game" application. 
 * "A boring game" is a very boring text based adventure game.
 * 
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Daniëlle van der Tuin
 * @version 23-1-2020
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.

    GO("go"),QUIT("quit"), HELP("help"), UNKNOWN("?"), BACK("back"), ABOUT("about"), PLAY("play"), PICKUP("pickup"), CATCH("catch");    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}