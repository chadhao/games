
import java.util.Random;


public class Board {
    
    private static Random rand = new Random();
    private Tile[][] gameBoard;
    private int score;
    private int step;

    public Board() {
	score = 0;
	step = 0;
	gameBoard = new Tile[4][4];
	for(int i = 0; i < 4; i++) {
	   for(int j = 0; j < 4; j++) {
	       gameBoard[i][j] = new Tile(0);
	   } 
	}
    }
    
    private void init() {
	
    }
    
    private void randomFill() {
	while(true) {
	    int x = rand.nextInt(4);
	    int y = rand.nextInt(4);
	}
    }
    
    public void printBoard() {
	for(int i = 0; i < 4; i++) {
	   for(int j = 0; j < 4; j++) {
	       System.out.print(gameBoard[i][j].getValue() + " ");
	   }
	   System.out.println();
	}
    }
    
    public boolean isFull() {
	for(Tile[] row : gameBoard) {
	    for(Tile aTile : row) {
		if (aTile.isEmpty()) {
		    return false;
		}
	    }
	}
	return true;
    }
    
    public boolean isMovable() {
	if (!isFull()) {
	    return true;
	}
	for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 4; j++) {
		try {if (gameBoard[i][j].getValue()==gameBoard[i+1][j].getValue()) return true;} catch (ArrayIndexOutOfBoundsException e) {}
		try {if (gameBoard[i][j].getValue()==gameBoard[i-1][j].getValue()) return true;} catch (ArrayIndexOutOfBoundsException e) {}
		try {if (gameBoard[i][j].getValue()==gameBoard[i][j+1].getValue()) return true;} catch (ArrayIndexOutOfBoundsException e) {}
		try {if (gameBoard[i][j].getValue()==gameBoard[i][j-1].getValue()) return true;} catch (ArrayIndexOutOfBoundsException e) {}
	    }
	}
	return false;
    }
    
}
