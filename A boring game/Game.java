import java.util.*;
/**
 *  This class is the main class of the "A boring game" application. 
 *  "A boring game" as the name suggests is a very boring, text based adventure game.  Users 
 *  can walk around some scenery. and interact with some monsters. 
 *  so much fun
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the keys array,creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Daniëlle van der Tuin
 * @version 23-1-2020
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack <Room> rooms;// een stack om voor de back functie de kamers in op te slaan
    private int visitedShop = 0;// variabel om bij te houden of iemand al in de shop geweest is
    int keys[];// hier worden de sleutels in opgeslagen die je verzameld
    int bigKey = 0;// zodra je 5 sleutels hebt word bigKey 1
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        createKeys();
        parser = new Parser();
        rooms = new Stack();// een stack om voor de back functie de kamers in op te slaan
    }
    
    /**
     * Create all the slots for the keys.
     * keep checking if keys had reached a length of 5.
     * if true then create the big key
     */
    private void createKeys(){
        keys = new int[5];
        if (keys.length == 5){
            bigKey = 1;
        }      
      
     }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms(){
        Room myHouse, town, shop, house1, house2, house3, caveEntrance, caveRoom1, caveRoom2, trapRoom, trap, bossRoom, caveRoom5, caveRoom6, caveRoom7, theEnd, key1, key2, key3, key4, key5, defeat;
      
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
            "He doesn't want to hear anything from you and says you are stupid for even thinking about going into that cave, 'it is way to dangerous' he said"+"\n"+
            "He tells you that to even have a chance you need the best sword in the townshop",true,"house2");
        house3 = new Room("in the brick house, there is a young man here, you recognize him as the weird adventurer who isn't in town often"+"\n"+
            "You ask him if he has ever been in the cave to which he responds that he doesn't dare to go in,"+"\n"+
            "because of the huge monster that is rumoured to be in the cave."+"\n"+
            "He tells you that you might be able to do it, but to be very carefull with how you treat those monsters"+"\n"+
            "Some monsters have feelings, others however need to be defeated quickly",true,"house3");
        caveEntrance = new Room("in a big cave, you see a few tunnels leading deeper into the cave the middle one seems to be locked.",false,"caveEntrance");
        caveRoom1 = new Room("in the first room left to the enterance of the cave."+"\n"+
            "It's a small gloomy room with a small ghost weeping in the corner."+"\n"+
            "You ask the ghost what is wrong and he tells you he doesn't like being a ghost, because everyone is afraid of him.",true,"caveRoom1");
        caveRoom2 = new Room("in a new room as you walk in you notice half eaten carrots everywhere."+"\n"+
            "Then out of nowhere a rabid rabbit comes charging at you",true,"caveRoom2");
        //traproom
        trapRoom = new Room("in a tunnel, as you keep on walking you notice a hole in the ground"+"\n"+
            "You look down into the hole and see something shiny at the bottom. you are contemplating whether to go into the hole or not",true,"trapRoom");
        trap = new Room("going into the hole. You slip and die. So that was a horrible decision, the shiny thing at the bottom of the hole was a rock"+"\n"+
            "It's pretty sad but you're gonna have to start over now" ,true,"trap");
            
        bossRoom = new Room("in the locked room, this seem to be big boss room you see your mom in the corner. you kill big bad guy",false,"bossRoom");
        
        caveRoom5 = new Room("entering a new room as you walk in you hear weird noises but can't really see where it comes from because it's too dark."+"\n"+
            "all of the sudden a skeleton apears in front of you, he is wearing a stylish green suit."+"\n"+
            "The skeleton greets you.",true, "caveRoom5");
        caveRoom6 = new Room("in a room covered by a layer of thick blue slime, in the corner of the room you see a blue slime."+"\n"+
            "he seems pretty friendly, but then you notice he is giving you a really weird stare",true,"caveRoom6");
        caveRoom7 = new Room("in a new room, as you enter a happy dog greets you, he looks lost."+"\n"+
            "You recognize the dog, you may have seen him wandering around the village before.",true,"caveRoom7");
        // keys     
        key1 = new Room("excited, you obtain the first piece of a key",true,"key1");
        key2 = new Room("excited, you obtain the second piece of a key",true,"key2");
        key3 = new Room("excited, you obtain the third piece of a key",true,"key3");
        key4 = new Room("excited, you obtain the fourth piece of a key",true,"key4");
        key5 = new Room("excited, you obtain the fifth piece of a key",true,"key5");
        defeat = new Room("very sad, you made a wrong decision."+"\n"+
            "You're gonna have to start over now",true,"death");
        
        theEnd = new Room("done wow, you have defeated the big bad guy and got your mom back, congrats!"+"\n"+
            "we hope you somewhat enjoyed this little game"+"\n"+
            "You can go and quit the game now you did it",true,"theEnd");
        
        // initialise room exits
        myHouse.setExit("outside", town);
        //in town  
        town.setExit("cave", caveEntrance);
        town.setExit("wood.house", house1);
        town.setExit("old.house", house2);
        town.setExit("brick.house", house3);
        town.setExit("shop", shop);
        // back to town
        shop.setExit("outside", town);
        house1.setExit("outside", town);
        house2.setExit("outside", town);
        house3.setExit("outside", town);
        //in cave
        caveEntrance.setExit("outside", town);
        caveEntrance.setExit("left", caveRoom1);
        caveEntrance.setExit("less.left", caveRoom2);
        caveEntrance.setExit("mid-left", trapRoom);
        caveEntrance.setExit("middle.room", bossRoom);
        caveEntrance.setExit("mid-right", caveRoom5);
        caveEntrance.setExit("less.right", caveRoom6);
        caveEntrance.setExit("right", caveRoom7);
        
        trapRoom.setExit("main.cave", caveEntrance);
        trapRoom.setExit("hole", trap);
        
        //fight opties
        caveRoom1.setExit("fight", defeat);
        caveRoom1.setExit("peaceful", key1);
        key1.setExit("main.cave", caveEntrance);
        
        caveRoom2.setExit("fight", key2);
        caveRoom2.setExit("peaceful", defeat);
        key2.setExit("main.cave", caveEntrance);
        
        caveRoom5.setExit("fight", defeat);
        caveRoom5.setExit("peaceful", key3);
        key3.setExit("main.cave", caveEntrance);
        
        caveRoom6.setExit("fight", key4);
        caveRoom6.setExit("peaceful", defeat);
        key4.setExit("main.cave", caveEntrance);
        
        caveRoom7.setExit("fight", defeat);
        caveRoom7.setExit("peaceful", key5);
        key5.setExit("main.cave", caveEntrance);
        
        bossRoom.setExit("fight", theEnd);
        bossRoom.setExit("peaceful", defeat);
        
        
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
        System.out.println("Thank you for playing."+"\n"+"Bye bye.");
    
    }

    /**
     * Print out the first room description.
     */
    private void printWelcome(){
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
        System.out.println("Type 'go' followed by the direction you wish to go" );
    }
    
    /**
     * Print out the menu.
     */
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
     * check for certain requirements go enter a room
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
            
            currentRoom = rooms.elementAt(0);// je gaat dood dus ga je terug naar de kamer waar je bent begonnen die is opgeslagen op plek 0
            rooms.clear();// om te zorgen dat back niet kan worden gebruikt word de stack gewist
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
        }
         else if(nextRoom.name == "death"){
            currentRoom = nextRoom;
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
            
            currentRoom = rooms.elementAt(0);
            rooms.clear();
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
        }
        else if(nextRoom.name == "bossRoom" &&  bigKey == 1){//zodra je een grote sleutel hebt kan je de boss room in
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
        }
        
        else if(nextRoom.access == true || nextRoom.name == "caveEntrance" && visitedShop > 0){//als de shop is bezocht kan je in de cave
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            if (currentRoom.name == "shop"){//check of je in de shop bent zo ja zet variabel op 1
                visitedShop = 1;
            }
            if (currentRoom.name == "key1"){// key1 is een ruimte waar je heen gaat als je de goede keuze hebt gemaakt in een monster kamer
                 keys[0] = 1; // goede keuze dus key1 is behaald
            }
            if (currentRoom.name == "key2"){
                 keys[1] = 1;
             }
            if (currentRoom.name == "key3"){
                 keys[2] = 1;
             }
            if (currentRoom.name == "key4"){
                 keys[3] = 1;
             }
            if (currentRoom.name == "key5"){
                 keys[4] = 1;
             }
            System.out.println("visitedshop = " + visitedShop);
            for (int i = 0; i < keys.length; i++){ 
                System.out.println("key at index " + i +" : "+ keys[i]); 
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
    
    /** 
     * "Back" was entered. 
     * Check if it is possible to go back.
     * If going back is possible go back.
     * If it is not possible print messsage.
     */
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
