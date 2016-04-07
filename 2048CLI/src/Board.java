
import java.util.ArrayList;
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
	init();
    }
    
    private void init() {
	randomFill();
	randomFill();
    }
    
    public void test() {
	for(int i = 0; i < 14; i++) {
	    randomFill();
	}
    }
    
    private boolean randomFill() {
	if (isFull()) {
	    return false;
	}
	ArrayList<Tile> emptyTiles = new ArrayList<>();
	for(int i = 0; i < 4; i++) {
	   for(int j = 0; j < 4; j++) {
	       if (gameBoard[i][j].isEmpty()) emptyTiles.add(gameBoard[i][j]);
	   } 
	}
	emptyTiles.get(rand.nextInt(emptyTiles.size())).setValue((rand.nextInt(10)==0?4:2));
	return true;
    }
    
    public void printBoard() {
	for(int i = 0; i < 4; i++) {
	   for(int j = 0; j < 4; j++) {
	       System.out.print(gameBoard[i][j].getValue() + " ");
	   }
	   System.out.println();
	}
	System.out.println("Score: " + score);
	System.out.println("Steps: " + step);
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
    
    private void mergeUp() {
	for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 3; j++) {
		if (gameBoard[j][i].isEmpty()) continue;
		for(int k = j+1; k < 4; k++) {
		    if (!gameBoard[k][i].isEmpty()&&gameBoard[k][i].getValue()!=gameBoard[j][i].getValue()) break;
		    if (gameBoard[j][i].getValue()==gameBoard[k][i].getValue()) {
			gameBoard[j][i].setValue(gameBoard[j][i].getValue()*2);
			gameBoard[k][i].setValue(0);
			score += gameBoard[j][i].getValue();
			break;
		    }
		}
	    }
	}
    }
    
    public void moveUp() {
	mergeUp();
	for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 3; j++) {
		if (!gameBoard[j][i].isEmpty()) continue;
		for(int k = j+1; k < 4; k++) {
		    if (!gameBoard[k][i].isEmpty()) {
			gameBoard[j][i].setValue(gameBoard[k][i].getValue());
			gameBoard[k][i].setValue(0);
			break;
		    }
		}
	    }
	}
	step++;
    }
    
}
