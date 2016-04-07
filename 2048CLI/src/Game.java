
public class Game {
    
    public static void main(String[] args) {
	Board game = new Board();
	game.printBoard();
	System.out.println(game.isFull()?"Full":"Not Full");
	System.out.println(game.isMovable()?"Movable":"Not movable");
    }
    
}
