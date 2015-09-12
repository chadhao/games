
public class Deck {
	private int[] communityCards;
	
	public Deck() {
		this.communityCards = new int[5];
	}
	
	public void receiveCard(int which, int card)
	{
		this.communityCards[which] = card;
	}
	
	public int getCard(int which)
	{
		return this.communityCards[which];
	}
}
