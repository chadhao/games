
public class Player {
	private String name;
	private int chips;
	private boolean isAllin;
	private boolean isFold;
	private boolean isBb;
	private boolean isSb;
	private boolean isDb;
	private int[] inHand;
	private int cardPower;
	
	public Player() {
		this.inHand = new int[2];
	}
	
	public void receiveCard(int which, int card) {
		this.inHand[which] = card;
	}
	
	public int getCard(int which)
	{
		return this.inHand[which];
	}
}
