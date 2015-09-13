import java.util.Scanner;

public class PopStar
{

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		Game aGame = new Game();
		while (true)
		{
			System.out.println(aGame);
			Star.selectStars(aGame, keyboard.nextInt(), keyboard.nextInt(), 0);
			Star.popStars(aGame);
		}
		
	}

}
