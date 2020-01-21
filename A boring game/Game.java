import java.util.*;
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
 * @author  me
 * @version 0
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack <Room> rooms;
    private int visitedShop = 0;
    
    public static void main(String[] args){
        Game g = new Game();
        g.play();
    }
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        rooms = new Stack();
    }
 
    private void createItem(){
       Item key1, key2, key3, key4, key5; 
       
       key1 = new Item();
       key2 = new Item();
       key3 = new Item();
       key4 = new Item();
       key5 = new Item();
       
       key1.setItem("1", key1);
       
       key2.setItem("2", key2);
       
       key3.setItem("3", key3);
       
       key4.setItem("4", key4);
       
       key5.setItem("5", key5);
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms(){
        Room myHouse, town, shop, house1, house2, house3, caveEntrance, caveRoom1, caveRoom2, trapRoom, trap, caveRoom4, caveRoom5, caveRoom6, caveRoom7, theEnd;
      
        // create the rooms
        myHouse = new Room("in your house, you just woke up and decided to get some food." +"\n"+
            "As you walk into your living room you see that there is dirt everywhere, you call for you mother but no one awnsers." +"\n"+
            "You start to think, your mom is always at home so where is she? She would tell you if she went into town"+"\n"+
            "You get the idea that maybe a monster from the cave came into the house and took your mom,"+"\n"+
            "judging from the dirt and small pebbles scattered all over the house.",true,"myHouse");
        town = new Room("in the town, you decide to ask a few people if they have seen your mother or if they know more about the cave.",true,"town");
        shop = new Room("in the shop, you wanna get the best goodies but you have no money."+"\n"+
            "You try to convince the shopkeeper to give you something for free which he does!! LUCKY YOU",true,"shop");
        house1 = new Room("in the wooden house, there is a nice lady here." +"\n"+
            "This lady sure does know a lot about your mother, she seems to be kind of a stalker."+"\n"+
            "While you are a bit creeped out, she did tell you that she heard your mom scream,"+"\n"+
            "now you are sure that those monster took her and you are going to get her back",true,"house1");
        house2 = new Room("in the old house, there is a grumpy old man here." +"\n"+
            "He doesn't want to hear anything from you and says you are stupid for even thinking about going into that cave, 'it is much to dangerous' he said"+"\n"+
            "He tells you that to even have a chance you need the best sword in the townshop",true,"house2");
        house3 = new Room("in the brick house, there is a young man here, you recognize him as the weird adventurer who isn't in town often"+"\n"+
            "You ask him if he has ever been in the cave to which he reponds that he doesn't dare to go in,"+"\n"+
            "because of the huge monster that is rumoured to be in the cave."+"\n"+
            "He tells you that you might be able to do it, but to be very carefull with how you treat those monsters"+"\n"+
            "Some monsters have feelings, others however need to be defeated quickly",true,"house3");
        caveEntrance = new Room("in a big cave, you see a few tunnels leading deeper into the cave the middle one seems to be locked.",false,"caveEntrance");
        caveRoom1 = new Room("in the cave",true,"caveRoom1");
        caveRoom2 = new Room("in the cave",true,"caveRoom2");
        trapRoom = new Room("in a tunnel, as you keep on walking you notice a hole in the ground"+"\n"+
            "You look down into the hole and see something shiny at the bottom. you are contemplating whether to go into the hole or not",true,"trapRoom");
        trap = new Room("you slip and die. So that was a horrible desicion, the shiny thing at the bottom of the hole was a rock"+"\n"+
            "It's pretty sad but you are gonna have to start over now" ,true,"trap");
        caveRoom4 = new Room("in the cave",false,"caveRoom4");
        caveRoom5 = new Room("in the cave",true, "caveRoom5");
        caveRoom6 = new Room("in the cave",true,"caveRoom6");
        caveRoom7 = new Room("in the cave",true,"caveRoom7");
        theEnd = new Room("Done wow, you have defeated the big bad guy and got your mom back, congrats!"+"\n"+
            "we hope you somewhat enjoyed this little game"+"\n"+
            "You can go and quit the game now you did it",false,"theEnd");
        
        // initialise room exits
        myHouse.setExit("outside", town);
         
        town.setExit("cave", caveEntrance);
        town.setExit("wood.house", house1);
        town.setExit("old.house", house2);
        town.setExit("brick.house", house3);
        town.setExit("shop", shop);
        
        shop.setExit("outside", town);
        

        house1.setExit("outside", town);
        house2.setExit("outside", town);
        house3.setExit("outside", town);
        
        caveEntrance.setExit("outside", town);
        caveEntrance.setExit("left", caveRoom1);
        caveEntrance.setExit("less.left", caveRoom2);
        caveEntrance.setExit("mid-left", trapRoom);
        caveEntrance.setExit("middle.room", caveRoom4);
        caveEntrance.setExit("mid-right", caveRoom5);
        caveEntrance.setExit("less.right", caveRoom6);
        caveEntrance.setExit("right", caveRoom7);
        
        caveRoom1.setExit("main.cave", caveEntrance);
        caveRoom2.setExit("main.cave", caveEntrance);
        trapRoom.setExit("main.cave", caveEntrance);
        trapRoom.setExit("hole", trap);
        caveRoom4.setExit("main.cave", caveEntrance);
        caveRoom4.setExit("theEnd", theEnd);
        caveRoom5.setExit("main.cave", caveEntrance);
        caveRoom6.setExit("main.cave", caveEntrance);
        caveRoom7.setExit("main.cave", caveEntrance);
       
        currentRoom = myHouse;  // start game in  house
    }


    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play(){            
        printMenu();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.          
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing."+"\n"+" Bye bye.");
    
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome(){
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
        System.out.println("Type 'go' followed by the direction you wish to go" );
    }
    
    private void printMenu(){
        System.out.println();
        System.out.println("Welcome to our game!");
        System.out.println();
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println("Type 'play' to start.");
        System.out.println("Type 'about' to learn more about the game.");
        System.out.println("Type 'quit' to quit the game.");
     }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command){
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
                
            case BACK:
                back(command);
                break;
                
            case ABOUT:
                System.out.println("This game was made by Daniëlle."+"\n"+"nothing more intresting to it i guess");
                break;
                
            case PLAY:
                printWelcome();
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
    private void printHelp(){
        System.out.println("You don't know what to do");
        System.out.println("But then somehow a list of commands pops into your head");
        System.out.println("You remember what your command words are!");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Uhmmm go where?");
            return;
        }
        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
      
        if (nextRoom == null) {
            System.out.println("Well, you can try but that's not a direction i know");
        }
        else if(nextRoom.name == "trap"){
            currentRoom = nextRoom;
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
            
            currentRoom = rooms.elementAt(0);
            rooms.clear();
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
        }
        else if(nextRoom.access == true ||
        nextRoom.name == "caveEntrance" && visitedShop > 0){
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            
            if (currentRoom.name == "shop"){
                visitedShop = 1;
             }
            
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
        }
        else{
            System.out.println("I think you need something to go here");
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command){
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    } 
    
    private void back(Command command) {
        if(rooms.size()>0){
           currentRoom = rooms.pop();
           System.out.println(currentRoom.getLongDescription());
        }
        else{
           System.out.println("You can't go back any further");
        }
    }
    
}
