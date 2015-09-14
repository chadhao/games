import java.util.Random;

public class Game {
	private int[][] deck;
	private Random rand;
	
	public Game()
	{
		deck = new int[4][4];
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				deck[i][j] = 0;
			}
		}
		rand = new Random();
		fillBlock();
		fillBlock();
	}
	
	public String toString()
	{
		String printDeck = "";
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				printDeck += "["+deck[i][j]+"]";
				printDeck += " ";
			}
			printDeck += "\n";
		}
		return printDeck;
	}
	
	private int getRandom(int high)
	{
		return rand.nextInt(high+1);
	}
	
	public void fillBlock()
	{
		while(true)
		{
			int x = getRandom(3);
			int y = getRandom(3);
			if (deck[x][y] == 0)
			{
				deck[x][y] = (getRandom(1)+1)*2;
				break;
			}
		}
	}
}
