/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room myHouse, town, house1, house2, house3, caveEntrance, caveRoom1, caveRoom2, caveRoom3, caveRoom4, caveRoom5, caveRoom6, caveRoom7;
      
        // create the rooms
        myHouse = new Room("in your house, you just woke up and decided to get some food." +"\n"+ " As you walk into you living room you see that there is dirt everywhere, you call for you mother but no one awnsers." +"\n"+" you think it may have been a cave monster judging from the dirt and small pebbles scattered all over the house.");
        town = new Room("in the town, you decide to ask a few people if they have seen you mother or if they know more abot the cave.");
        house1 = new Room("in the blue house, there is a nice lady here.");
        house2 = new Room("in the red house, there is a grumpy old man here.");
        house3 = new Room("in the brown house, there is a young man here, you recognize him as the weird adventurer.");
        caveEntrance = new Room("in a big cave, you see a few tunnels leading deeper into the cave the middle one seems to be locked.");
        caveRoom1 = new Room("in the cave");
        caveRoom2 = new Room("in the cave");
        caveRoom3 = new Room("in the cave");
        caveRoom4 = new Room("in the cave");
        caveRoom5 = new Room("in the cave");
        caveRoom6 = new Room("in the cave");
        caveRoom7 = new Room("in the cave");
        
        
        // initialise room exits
        myHouse.setExit("outside", town);
         
        town.setExit("cave", caveEntrance);
        town.setExit("blue house", house1);
        town.setExit("red house", house2);
        town.setExit("brown house", house3);

        house1.setExit("outside", town);

        house2.setExit("outside", town);

        house3.setExit("outside", town);
        
        caveEntrance.setExit("outside", town);
        caveEntrance.setExit("left", caveRoom1);
        caveEntrance.setExit("little less left", caveRoom2);
        caveEntrance.setExit("mid-left", caveRoom3);
        caveEntrance.setExit("middle room", caveRoom4);
        caveEntrance.setExit("mid-right", caveRoom5);
        caveEntrance.setExit("little less right", caveRoom6);
        caveEntrance.setExit("right", caveRoom7);
        
        caveRoom1.setExit("main cave", caveEntrance);
        caveRoom2.setExit("main cave", caveEntrance);
        caveRoom3.setExit("main cave", caveEntrance);
        caveRoom4.setExit("main cave", caveEntrance);
        caveRoom5.setExit("main cave", caveEntrance);
        caveRoom6.setExit("main cave", caveEntrance);
        caveRoom7.setExit("main cave", caveEntrance);

        currentRoom = myHouse;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to our game!");
        System.out.println("this is an incredibly boring adventure game. but at least try to enjoy it");
        System.out.println("in this game you will be playing as a young adventurous boy" );
        System.out.println ();
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
             case UNKNOWN:
                System.out.println("I don't know what you mean... Try a different command");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You don't know what to do");
        System.out.println("But then somehow a list of commands pops into your head");
        System.out.println();
        System.out.println("You remember what your command words are!");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Uhmmm go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("well, you can try but that's a wall");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
