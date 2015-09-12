import java.util.Random;

public class Card {
	private String[] suit = {"黑桃", "红桃", "梅花", "方块"};
	private String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private static String[] card;
	private int cardNow;
	
	public Card() {
		Card.card = new String[52];
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 13; j++)
			{
				Card.card[i*13+j] = this.suit[i] + this.rank[j];
			}
		}
		this.cardNow = 0;
	}
	
	public void shuffleCards()
	{
		int i = 52;
		int j;
		Random rand = new Random();
		String temp;
		
		while((--i)>=0)
		{
			j = rand.nextInt(i+1);
			temp = Card.card[i];
			Card.card[i] = Card.card[j];
			Card.card[j] = temp;
		}
		this.cardNow = 0;
	}
	
	public static String getCardName(int whichCard)
	{
		return Card.card[whichCard];
	}
	
	public int getCardValue(int whichCard)
	{
		int value;
		try
		{
			value = Integer.parseInt(String.valueOf(Card.card[whichCard].charAt(2)));
			if (value == 1)
			{
				value = 10;
			}
		}
		catch (NumberFormatException ne)
		{
			if (Card.card[whichCard].charAt(2) == 'J')
			{
				value = 11;
			}
			else if (Card.card[whichCard].charAt(2) == 'Q')
			{
				value = 12;
			}
			else if (Card.card[whichCard].charAt(2) == 'K')
			{
				value = 13;
			}
			else
			{
				value = 14;
			}
		}
		return value;
	}
	
	public int dealCard()
	{
		this.cardNow++;
		return this.cardNow-1;
	}
	
	public int getCardNow()
	{
		return cardNow;
	}
	
	public String[] getAllCards()
	{
		return Card.card;
	}
}
