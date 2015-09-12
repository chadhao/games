
public class Game {
	private int[][] deck;
	
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
}
