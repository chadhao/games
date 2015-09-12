import java.util.Random;

public class Star
{
	private static Random rand;
	
	static
	{
		rand = new Random();
	}
	
	public static int getStar()
	{
		return rand.nextInt(5)+1;
	}
	
	public static void selectStars(Game thisGame, int x, int y, int color)
	{
		if (x < 0 || x >= Game.SIZE_OF_BOARD || y < 0 || y >= Game.SIZE_OF_BOARD)
		{
			return;
		}
		if (thisGame.getDeckSelected(x, y))
		{
			return;
		}
		if (thisGame.getDeck(x, y) == 0)
		{
			return;
		}
		if (color == 0)
		{
			thisGame.setSelectCount(0);
			color = thisGame.getDeck(x, y);
		}
		if (thisGame.getDeck(x, y) != color)
		{
			return;
		}
		thisGame.setDeckSelected(x, y, true);
		thisGame.setSelectCount(thisGame.getSelectCount()+1);
		selectStars(thisGame, x, y-1, color);
		selectStars(thisGame, x-1, y, color);
		selectStars(thisGame, x, y+1, color);
		selectStars(thisGame, x+1, y, color);
		if (thisGame.getSelectCount() < 2)
		{
			for (int i = 0; i < Game.SIZE_OF_BOARD; i++)
			{
				for (int j = 0; j < Game.SIZE_OF_BOARD; j++)
				{
					thisGame.setDeckSelected(i, j, false);
				}
			}
		}
	}
	
	public static void popStars(Game thisGame)
	{
		for (int i = 0; i < Game.SIZE_OF_BOARD; i++)
		{
			for (int j = 0; j < Game.SIZE_OF_BOARD; j++)
			{
				if (thisGame.getDeckSelected(i, j))
				{
					thisGame.setDeckSelected(i, j, false);
					thisGame.setDeck(i, j, 0);
				}
			}
		}
	}
}
