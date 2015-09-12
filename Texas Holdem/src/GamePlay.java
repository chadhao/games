
public class GamePlay {

	public static void main(String[] args) {
		int numOfPlayer = 5;
		
		Card card = new Card();
		Deck deck = new Deck();
		Player[] player = new Player[numOfPlayer];
		
		card.shuffleCards();
		
		for (int i = 0; i < numOfPlayer; i++)
		{
			player[i] = new Player();
		}
		
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < numOfPlayer; j++)
			{
				player[j].receiveCard(i, card.dealCard());
			}
		}
		
		card.dealCard();
		
		for (int i = 0; i < 3; i++)
		{
			deck.receiveCard(i, card.dealCard());
		}
		
		card.dealCard();
		deck.receiveCard(3, card.dealCard());
		card.dealCard();
		deck.receiveCard(4, card.dealCard());
		
		for (int i = 0; i < numOfPlayer; i++)
		{
			System.out.print("Player"+(i+1)+": ");
			for (int j = 0; j < 2; j++)
			{
				System.out.print(Card.getCardName(player[i].getCard(j)) + " ");
			}
			System.out.println("");
		}
		
		System.out.print("Deck: ");
		for (int i = 0; i < 5; i++)
		{
			System.out.print(Card.getCardName(deck.getCard(i)) + " ");
		}
		System.out.println("");
		
		System.out.println("Card now: " + card.getCardNow());
		
		
	}

}
