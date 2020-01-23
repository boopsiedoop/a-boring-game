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
    private Stack <Room> rooms; // a stack to store the rooms in
    private int visitedShop = 0;// a variable to keep track of store visits
    private int keys[];// to store the collected key pieces
    private int bigKey = 0;// when you have obtained 5 pieces a big key is made 
    private int bone = 0;
    private int butterfly = 0;
    private int pocketGoop = 0;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        createKeys();
        parser = new Parser();
        rooms = new Stack();
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
        Room myHouse, town, shop, house1, house2, house3, caveEntrance, spoopRoom, rabbitRoom, trapRoom, trap, bossRoom, skellyRoom, goopRoom, doggoRoom,
        theEnd, key1, key2, key3, key4, key5, defeat, finish;
      
        // create the rooms
        // town
        myHouse = new Room("in your house, you just woke up and decided to get some food." +"\n"+
            "As you walk into your living room you see that there is dirt everywhere, you call for you mother but no one awnsers." +"\n"+
            "You start to think, your mom is always at home so where is she? She would tell you if she went into town."+"\n"+
            "You get the idea that maybe a monster from the cave came into the house and took your mom,"+"\n"+
            "judging from the dirt and small pebbles scattered all over the house.",true,"myHouse");  
        town = new Room("in the town, you decide to ask a few people if they have seen your mother or if they know more about the cave."+"\n"+
            "There is also a small butterfly bush in the town."+"\n"+
            "If you want to you can try to catch one."+"\n"+
            "Type 'catch' to try and catch one.",true,"town");
        shop = new Room("in the shop, you wanna get the best goodies but you have no money."+"\n"+
            "You try to convince the shopkeeper to give you something for free which he does!! LUCKY YOU."+"\n"+
            "He gives you a sword.",true,"shop");
        house1 = new Room("in the wooden house, there is a nice lady here." +"\n"+
            "This lady sure does know a lot about your mother, she seems to be kind of a stalker."+"\n"+
            "While you are a bit creeped out, she did tell you that she heard your mom scream,"+"\n"+
            "now you are sure that those monster took her and you are going to get her back.",true,"house1");
        house2 = new Room("in the old house, there is a grumpy old man here." +"\n"+
            "He doesn't want to hear anything from you and says you are stupid for even thinking about going into that cave,"+"\n"+
            "'it is way to dangerous' he said."+"\n"+
            "He tells you that to even have a chance you need the best sword in the townshop."+"\n"+
            "While talking to the old man you notice a bone on the ground. you get the urge to pick it up." +"\n"+
            "\n"+
            "To pickup the bone type 'pickup'.",true,"house2");
        house3 = new Room("in the brick house, there is a young man here, you recognize him as the weird adventurer who isn't in town often."+"\n"+
            "You ask him if he has ever been in the cave to which he responds that he doesn't dare to go in,"+"\n"+
            "because of the huge monster that is rumoured to be in the cave."+"\n"+
            "He tells you that you might be able to do it, but to be very carefull with how you treat those monsters."+"\n"+
            "Some monsters have feelings, others however need to be defeated quickly.",true,"house3");
        // cave
        caveEntrance = new Room("in a big cave opening, you see a few tunnels leading deeper into the cave,"+"\n"+
            "the middle one has a door which seems to be locked."+"\n"+
            "You wil need a big key made of 5 different parts to open it.",false,"caveEntrance");
        spoopRoom = new Room("in the room left to the enterance of the cave."+"\n"+
            "It's a small gloomy room with a small ghost weeping in the corner."+"\n"+
            "You ask the ghost what is wrong and he tells you he doesn't like being a ghost, because everyone is afraid of him."
            +"\n"+"Will you fight or be peaceful with this monster?",true,"caveRoom1");
        rabbitRoom = new Room("in a new room as you walk in you notice half eaten carrots everywhere."+"\n"+
            "Then out of nowhere a rabid rabbit comes charging at you."
            +"\n"+"Will you fight or be peaceful with this monster?",true,"caveRoom2");
        skellyRoom = new Room("entering a new room as you walk in you hear weird noises,"+"\n"+
            "but can't really see where it comes from because it's too dark."+"\n"+
            "all of a sudden a skeleton apears in front of you, he is wearing a stylish green suit."+"\n"+
            "The skeleton greets you."
            +"\n"+"Will you fight or be peaceful with this monster?",true, "caveRoom5");
        goopRoom = new Room("in a room covered by a layer of thick blue slime. Then you notice a blue slime."+"\n"+
            "You think that maybe that slime is responsible for the mess because he is also blue."+"\n"+
            "He seems pretty friendly, but then you notice he is giving you a really weird stare."+"\n"+
            "You get the urge to pick some slime up."+"\n"+"\n"+
            "To pickup the thick blue slime type 'pickup'."
            +"\n"+"Will you fight or be peaceful with this monster?",true,"caveRoom6");
        doggoRoom = new Room("in the room right from the entrance, as you enter a happy dog greets you, he looks lost."+"\n"+
            "You recognize the dog, you may have seen him wandering around the village before."
            +"\n"+"Will you fight or be peaceful with this monster?",true,"caveRoom7");
        // trap room    
        trapRoom = new Room("in a tunnel, as you keep on walking you notice a hole in the ground."+"\n"+
            "You look down into the hole and see something shiny at the bottom."+"\n"+
            "You are contemplating whether to go into the hole or not.",true,"trapRoom");
        trap = new Room("going into the hole. You slip and die. So that was a horrible decision."+"\n"+
            "The shiny thing at the bottom of the hole was a rock."+"\n"+
            "It's pretty sad but you're gonna have to start over now."+"\n"+
            "Luckily you do keep your items.",true,"trap");
        // keys + battle     
        key1 = new Room("You choose to be nice to the ghost. You talk for a bit about his feelings." +"\n"+ 
            "He tells you he feels better now after ranting for a bit."+"\n"+
            "He reveals that there is a keyfragment hidden behind a rock and hands it over to you." +"\n"+ 
            "You obtain the first piece of a key.",true,"key1");
        key2 = new Room("You choose to fight the rabbit. good call, you block him as he charges to your face."+"\n"+
            "He charges at you a few more times before you manage to pin him to the ground and kill him."+"\n"+
            "After you kill the rabbit he dissapears into thin air and only a key fragment remains."+"\n"+
            "You obtain the second piece of a key.",true,"key2");
        key3 = new Room("You choose to be nice to the skeleton."+"\n"+ 
            "You compliment his outfit which seems to bring him joy."+"\n"+
            "He is a true gentleman, so he will gladly tell you where the key fragment is hidden to help you find your mom."+"\n"+
            "You obtain the third piece of a key.",true,"key3");
        key4 = new Room("You choose to fight the blob, you take an aggressive stance."+"\n"+
            "The blob immediately goes into attack mode."+"\n"+
            "The blob keps trying to swallow you, to stop him you cut him into really small pieces."+"\n"+ 
            "In his belly you find a key fragment."+"\n"+
            "You obtain the fourth piece of a key.",true,"key4");
        key5 = new Room("You choose to be nice to the doggo, i mean who could hurt this angel."+"\n"+
            "As the dog snuggles up to you, you notice a key fragment hanging from it's collar."+"\n"+
            "You obtain the fifth piece of a key.",true,"key5");
        defeat = new Room("very sad, you made a wrong decision."+"\n"+
            "You're gonna have to start over now."+"\n"+
            "Luckily you do keep your items.",true,"death");
        //boss room   
        bossRoom = new Room("in the room located behind the big locked door,"+"\n"+
            "this seem to be the big boss room, you see your mom in the corner.",false,"bossRoom");
        finish = new Room("You choose to fight the boss, it isn't an easy fight, you struggle to land a hit."+"\n"+
            "After a few openings and landed hits he seems to be at the brink of death, then he goes into rage mode."+"\n"+
            "You notice there are less openings now as the boss is trying desperately to win."+"\n"+
            "Then you notice a small opening Go! now is you chance.",true, "finish");
        //end
        theEnd = new Room("done wow, you have defeated the big bad guy and got your mom back, congrats!"+"\n"+
            "I hope you somewhat enjoyed this little game"+"\n"+
            "You can go and quit the game now, you did it",true,"theEnd");
        
        // initialise room exits
        myHouse.setExit("outside", town);
        // in town  
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
        // in cave
        caveEntrance.setExit("outside", town);
        caveEntrance.setExit("left", spoopRoom);
        caveEntrance.setExit("less.left", rabbitRoom);
        caveEntrance.setExit("mid-left", trapRoom);
        caveEntrance.setExit("middle.room", bossRoom);
        caveEntrance.setExit("mid-right", skellyRoom);
        caveEntrance.setExit("less.right", goopRoom);
        caveEntrance.setExit("right", doggoRoom);
        
        trapRoom.setExit("main.cave", caveEntrance);
        trapRoom.setExit("hole", trap);
        
        // fight opties
        spoopRoom.setExit("fight", defeat);
        spoopRoom.setExit("peaceful", key1);
        key1.setExit("main.cave", caveEntrance);
        
        rabbitRoom.setExit("fight", key2);
        rabbitRoom.setExit("peaceful", defeat);
        key2.setExit("main.cave", caveEntrance);
        
        skellyRoom.setExit("fight", defeat);
        skellyRoom.setExit("peaceful", key3);
        key3.setExit("main.cave", caveEntrance);
        
        goopRoom.setExit("fight", key4);
        goopRoom.setExit("peaceful", defeat);
        key4.setExit("main.cave", caveEntrance);
        
        doggoRoom.setExit("fight", defeat);
        doggoRoom.setExit("peaceful", key5);
        key5.setExit("main.cave", caveEntrance);
        
        bossRoom.setExit("fight", finish);
        bossRoom.setExit("peaceful", defeat);
        finish.setExit("last.hit", theEnd);
        
        
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
                System.out.println("This game was made by Daniëlle."+"\n"+"Nothing more intresting to it i guess.");
                break;
                
            case PLAY:
                printWelcome();
                break;
                
            case PICKUP:
                pickUp();
                break;
                
            case CATCH:
                catchButterfly();
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
            
            currentRoom = rooms.elementAt(0);// when you die you go back to the first room which is located at position 0 in the stack
            rooms.clear();// after rooms is cleared using back is not possible anymore
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
        else if(nextRoom.name == "bossRoom" &&  bigKey == 1){// when bigkey is 1 the boss room can be entered
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
        }
        
        else if(nextRoom.access == true || nextRoom.name == "caveEntrance" && visitedShop > 0){//after going to the shop you can enter the cave
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            if (currentRoom.name == "shop"){// if the current room is shop you have visited shop so visitedShop = 1
                visitedShop = 1;
            }
            if (currentRoom.name == "key1"){// key1 is the room you go to after making the right choice in monster room 1
                 keys[0] = 1; // right choice so key1 is obtained
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
            if (currentRoom.name == "key5" && bone == 1){
                 keys[4] = 1;
                 System.out.println();
                 System.out.println("You remember picking up a bone earlier."+"\n"+
                    "You give the bone to the dog and he gives you a very grateful look."+"\n"+
                    "This makes you very happy.");
             }
            else if (currentRoom.name == "key5" && bone == 0){
                 keys[4] = 1;
            }
             
            // System.out.println("visitedshop = " + visitedShop);
            
            // for (int i = 0; i < keys.length; i++){ 
            //  System.out.println("key at index " + i +" : "+ keys[i]); 
            //} 
            // uncomment to test keys array 'keys' & shop variable!
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
     * If going back is possible, go back.
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
    
    /** 
     * "Pickup" was entered. 
     * Check if there is something to pickup.
     * If there is nothing to pickup print message.
     * If there is something to pickup, pick it up.
     */
    private void pickUp(){
        if(currentRoom.name == "house2"){
            bone = 1;
            System.out.println("You picked up a bone!");
            System.out.println("It's pretty disgusting, why did you do that?");
        }
        else if(currentRoom.name == "caveRoom6"){ 
            pocketGoop = 1;
            System.out.println("You take a handful of thick blue goop and stuff it into your pocket!");
            System.out.println("That's pretty disgusting, why did you do that?");
        }
        else{
            System.out.println("Uhh, there is nothing to pick up here");
        }
    }
    
    /** 
     * "Catch" was entered. 
     * Check if there is something to catch.
     * If there is nothing to catch print message.
     * If there is something to catch, catch it.
     */
    private void catchButterfly(){
        if(currentRoom.name == "town"){
            butterfly = 1;
            System.out.println("Wow, you caught a butterfly!"); 
        }
        else{
            System.out.println("Uhm, what are you trying to catch");
        }
    }
    
}
