
public class Power {
	private static int[] allCards;
	private int[] bestHand;
	
	public Power() {
		Power.allCards = new int[7];
		this.bestHand = new int[5];
	}
	
	public static int getPower(int[] cardInHand, int[] communityCards)
	{
		System.arraycopy(communityCards, 0, Power.allCards, 0, 5);
		System.arraycopy(cardInHand, 0, Power.allCards, 5, 2);
		return 0;
	}
	
	public boolean isRoyalFlush()
	{
		for (int i = 0; i < 7; i++)
		{
			if (Card.getCardName(Power.allCards[i]).charAt(2) == '1')
			{
				this.bestHand[0] = Power.allCards[i];
			}
			else if (Card.getCardName(Power.allCards[i]).charAt(2) == 'J')
			{
				this.bestHand[1] = Power.allCards[i];
			}
			else if (Card.getCardName(Power.allCards[i]).charAt(2) == 'Q')
			{
				this.bestHand[2] = Power.allCards[i];
			}
			else if (Card.getCardName(Power.allCards[i]).charAt(2) == 'K')
			{
				this.bestHand[3] = Power.allCards[i];
			}
			else if (Card.getCardName(Power.allCards[i]).charAt(2) == 'A')
			{
				this.bestHand[4] = Power.allCards[i];
			}
		}
		return false;
	}
}
