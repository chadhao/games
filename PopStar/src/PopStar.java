import java.util.Scanner;

public class PopStar
{

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		Game aGame = new Game();
		
		for (int i = 0; i < Game.SIZE_OF_BOARD; i++)
		{
			for (int j = 0; j < Game.SIZE_OF_BOARD; j++)
			{
				if (j == 3 || j == 4 || j == 5)
				{
					aGame.setDeck(i, j, 1);
				}
			}
		}
		
		while (true)
		{
			System.out.println(aGame);
			Star.selectStars(aGame, keyboard.nextInt(), keyboard.nextInt(), 0);
			Star.popStars(aGame);
			if (aGame.isOver())
			{
				System.out.println("Game over!");
				break;
			}
		}
		System.out.println(aGame);
		
	}

}
