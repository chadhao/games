
public class Game
{
	private int[][] deck;
	private boolean[][] deckSelected;
	private int selectCount;
	public static final int SIZE_OF_BOARD = 10;
	
	public Game()
	{
		selectCount = 0;
		deck = new int[SIZE_OF_BOARD][SIZE_OF_BOARD];
		deckSelected = new boolean[SIZE_OF_BOARD][SIZE_OF_BOARD];
		for (int i = 0; i < SIZE_OF_BOARD; i++)
		{
			for (int j = 0; j < SIZE_OF_BOARD; j++)
			{
				deck[i][j] = Star.getStar();
				deckSelected[i][j] = false;
			}
		}
	}
	
	public String toString()
	{
		String aString = "";
		for (int i = 0; i < SIZE_OF_BOARD; i++)
		{
			for (int j = 0; j < SIZE_OF_BOARD; j++)
			{
				aString += deck[i][j] + " ";
			}
			aString += "\n";
		}
		aString += "\n";
		for (int i = 0; i < SIZE_OF_BOARD; i++)
		{
			for (int j = 0; j < SIZE_OF_BOARD; j++)
			{
				if (deckSelected[i][j])
				{
					aString += 1 + " ";
				}
				else
				{
					aString += 0 + " ";
				}
			}
			aString += "\n";
		}
		return aString;
	}
	
	public boolean getDeckSelected(int x, int y)
	{
		return deckSelected[x][y];
	}
	
	public void setDeckSelected(int x, int y, boolean selected)
	{
		deckSelected[x][y] = selected;
	}
	
	public int getDeck(int x, int y)
	{
		return deck[x][y];
	}
	
	public void setDeck(int x, int y, int color)
	{
		deck[x][y] = color;
	}
	
	public int getSelectCount()
	{
		return selectCount;
	}
	
	public void setSelectCount(int selectCount)
	{
		this.selectCount = selectCount;
	}
	
	private void moveStarDown(int x, int y)
	{
		if (y <= 0)
		{
			return;
		}
		deck[y][x] = deck[y-1][x];
		moveStarDown(x, y-1);
		deck[0][x] = 0;
	}
	
	public void moveDeckDown()
	{
		for (int y = 0; y < SIZE_OF_BOARD; y++)
		{
			for (int x = 0; x < SIZE_OF_BOARD; x++)
			{
				if (deckSelected[y][x])
				{
					moveStarDown(x, y);
					deckSelected[y][x] = false;
				}
			}
		}
	}
	
	public void moveDeckLeft()
	{
		
	}
}
