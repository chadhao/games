package game;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import sun.net.www.content.audio.x_aiff;

public class Advantrue_MUD {
    public static Scanner keyboard = new Scanner(System.in);
    public static int numOfRooms;
    public static int[][] roomLayout;
    public static String[] descriptions;
    public static int[] gold;
    public static boolean[] dagger;
    public static int playerHealth;
    public static int playerGold;
    public static boolean playerDagger;
    public static int playerPosition;

    public static void main(String[] args) {
	while (true) {
	    initGame();
	    while (true) {
		roomInfo(playerPosition);
		if (getRand()) {
		    if (!initMonster()) {
			continue;
		    }
		    if (playerHealth <= 0) {
			System.out.println("You were defeated! Game over...");
			break;
		    }
		}
		if (explore()) {
		    break;
		}
	    }
	    break;
	}
    }
    
    public static int getRand(int low, int high) {
	return low+(new Random()).nextInt(high-low+1);
    }
    
    public static boolean getRand() {
	return (new Random()).nextBoolean();
    }
    
    public static void roomDetails() {
	System.out.println("Room Details");
	for (int i = 0; i < numOfRooms; i++) {
	    System.out.println("===Room " + (i+1) + "===");
	    System.out.print("Description: ");
	    System.out.println(descriptions[i]);
	    System.out.print("Connections: ");
	    for (int j = 0; j < roomLayout[i].length; j++) {
		System.out.print(roomLayout[i][j] + (j==roomLayout[i].length-1?"":", "));
	    }
	    System.out.println();
	    System.out.print("Items: ");
	    System.out.print(gold[i]==0 && !dagger[i]?"nothing...":"");
	    System.out.println((gold[i]==0?"":("gold("+gold[i]+")")) + (dagger[i]?" / dagger":""));
	    System.out.println();
	}
    }
    
    public static void readSettings() {
	BufferedReader in;
	try {
	    in = new BufferedReader(new FileReader("input/gamemap.txt"));
	    
	    numOfRooms = Integer.parseInt(in.readLine());
	    roomLayout = new int[numOfRooms][];
	    descriptions = new String[numOfRooms];
	    gold = new int[numOfRooms];
	    Arrays.fill(gold, 0);
	    dagger = new boolean[numOfRooms];
	    Arrays.fill(dagger, false);
	    
	    for (int i = 0; i < numOfRooms; i++) {
		int roomNum = Integer.parseInt(in.readLine());
		
		//read @description tag
		in.readLine();
		descriptions[i] = in.readLine();
		
		//read @connect tag
		in.readLine();
		String[] connect = in.readLine().split(",");
		int[] connect_int = new int[connect.length];
		for (int j = 0; j < connect.length; j++) {
		    connect_int[j] = Integer.parseInt(connect[j]);
		}
		roomLayout[i] = connect_int;
		
		//read @items tag
		in.readLine();
		String[] items = in.readLine().split(",");
		for (int j = 0; j < items.length; j++) {
		    if (items[j].toLowerCase().equals("gold")) {
			gold[i] = getRand(100, 300);
		    }
		    if (items[j].toLowerCase().equals("dagger")) {
			dagger[i] = true;
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public static void initGame() {
	readSettings();
	playerDagger = false;
	playerGold = 0;
	playerHealth = 100;
	playerPosition = 0;
    }
    
    public static void roomInfo(int roomNum) {
	System.out.println("\n===Room " + (roomNum + 1) + " Info===");
	System.out.print("Doors: ");
	for (int room : roomLayout[roomNum]) {
	    System.out.print("Door[" + room + "]  ");
	}
	System.out.println();
	System.out.print("Items: ");
	if (gold[roomNum] == 0 && !dagger[roomNum]) {
	    System.out.println("nothing...");
	} else {
	    System.out.println((gold[roomNum]==0?"":("gold("+gold[roomNum]+" NZD)")) + (dagger[roomNum]?" / dagger":""));
	}
	System.out.println("Your Health: " + playerHealth + "%");
    }
    
    public static boolean initMonster() {
	int monsterHealth = 100;
	System.out.println("\n===Battle Mode===");
	System.out.println("A monster appears!");
	System.out.println("You" + (playerDagger?" ":" do not ") + "have a dagger!");
	System.out.println("Use \"help\" to list battle commands");
	while(true) {
	    System.out.print("> ");
	    String userInput = keyboard.nextLine().toLowerCase();
	    if (userInput.equals("help")) {
		printBattleCommands();
	    } else if (userInput.equals("run")) {
		if (getRand(0, 9) < 2) {
		    System.out.println("Failed to run!");
		} else {
		    System.out.println("You ran out of Room " + (playerPosition+1) + "...");
		    playerPosition = roomLayout[playerPosition][getRand(0, roomLayout[playerPosition].length-1)]-1;
		    return false;
		}
	    } else if (userInput.equals("attacks")) {
		int playerDamage = playerDagger?getRand(50, 75):getRand(10, 60);
		monsterHealth -= playerDamage;
		System.out.println("You made " + playerDamage + "% damage to the monster!");
		if (monsterHealth <= 0) {
		    int dropGold = getRand(100, 300);
		    gold[playerPosition] += dropGold;
		    System.out.println("The monster is dead...");
		    System.out.println(dropGold + " NZD worth of gold dropped...");
		    System.out.println("You have won the battle!!!");
		    break;
		} else {
		    System.out.println("The monster has " + monsterHealth + "% of HP left.");
		}
	    } else {
		System.out.println("Command does not exist!");
		printBattleCommands();
		continue;
	    }
	    int monsterDamage = getRand(10, 60);
	    playerHealth -= monsterDamage;
	    System.out.println("Monster made " + monsterDamage + "% damage to you!");
	    System.out.println("You have " + (playerHealth<=0?0:playerHealth) + "% of HP left.");
	    if (playerHealth <= 0) break;
	}
	return true;
    }
    
    public static void printBattleCommands() {
	System.out.println("\n===Battle Commands===");
	System.out.println("attacks - Player attacks the monster in the room");
	System.out.println("run - Player runs away, going through a randomly selected door");
	System.out.println("help - Displays commands in this mode");
    }
    
    //return true if player successfully escaped.
    //return false if player opened another door.
    public static boolean explore() {
	System.out.println("\n===Explore Mode===");
	System.out.println("Use \"help\" to list explore commands");
	while (true) {
	    System.out.print("> ");
	    String[] userInput = keyboard.nextLine().toLowerCase().split(" ");
	    if (userInput.length == 1 && userInput[0].equals("info")) {
		roomInfo(playerPosition);
	    } else if (userInput.length == 1 && userInput[0].equals("help")) {
		printExploreCommands();
	    } else if (userInput.length == 1 && userInput[0].equals("search")) {
		if (playerPosition+1 == roomLayout.length) {
		    if (playerGold >= 1000) {
			return true;
		    } else {
			System.out.println("You found the way out!");
			System.out.println("But you need 1000 NZD worth of gold to escape!");
			System.out.println("Try wander around and find some more gold!");
			System.out.println("Current gold: " + playerGold + " NZD");
		    }
		} else {
		    System.out.println("There is no way out...");
		}
	    } else if (userInput.length == 2 && userInput[0].equals("pickup")) {
		pickupItems(userInput[1]);
	    } else if (userInput.length == 2 && userInput[0].equals("open")) {
		int newRoom = -1;
		try {
		    newRoom = Integer.parseInt(userInput[1]);
		} catch (Exception e) {
		    System.out.println("Please give me a valid room number!");
		}
		if (newRoom != -1 && roomExists(roomLayout[playerPosition], newRoom)) {
		    System.out.println("Moving to Room " + newRoom + "...");
		    playerPosition = newRoom-1;
		    return false;
		}
	    } else {
		System.out.println("Command does not exist!");
		printExploreCommands();
	    }
	}
    }
    
    public static boolean roomExists(int[] rooms, int room) {
	for (int r : rooms) {
	    if (r == room) {
		return true;
	    }
	}
	return false;
    }

    public static void printExploreCommands() {
	System.out.println("\n===Explore Commands===");
	System.out.println("info - Display room information");
	System.out.println("open [n] - Player opens the door labeled [n] and enters the room");
	System.out.println("pickup [item] - Player picks up an [item] in the room");
	System.out.println("search - The player searches the room to find the exit");
	System.out.println("help - Displays commands in this mode");
    }
    
    public static void pickupItems(String item) {
	if (item.equals("gold")) {
	    if (gold[playerPosition] > 0) {
		playerGold += gold[playerPosition];
		System.out.println("You found " + gold[playerPosition] + " NZD worth of gold!");
		System.out.println("You have " + playerGold + " NZD worth of gold now!");
		gold[playerPosition] = 0;
	    } else {
		System.out.println("I did not see any gold in this room...");
	    }
	} else if (item.equals("dagger")) {
	    if (!playerDagger && dagger[playerPosition]) {
		System.out.println("You found a dagger!");
		System.out.println("This may help in a battle.");
		dagger[playerPosition] = false;
		playerDagger = true;
	    } else if (!dagger[playerPosition]) {
		System.out.println("No dagger found in this room...");
	    }
	} else {
	    System.out.println("What is " + item + "?");
	}
    }
}
