import java.util.*; 
import java.util.ArrayList; //includes ArrayList

public class Deck {
	int RANK = 13;
	int SUIT = 4;
	ArrayList<Card>Dealer = new ArrayList<Card>();

	public Deck()
	{
		int i = 0;
		int j = 0;
		for (i = 0; i < RANK; i++)
		{
			for (j = 0; j < SUIT; j++)
			{
				Dealer.add(new Card(i, j));
			}
		}
		Collections.shuffle(Dealer, new Random()); //shuffles deck after values initialized

		//just cool debugging seeing the shuffle work
		for (i = 0; i < RANK; i++)
		{
			for (j = 0; j < SUIT; j++)
			{
				//System.out.println(Deck.get(i*4+j));
			}
		}
	}

	//dealer deals cards and then removes cards from deck after dealt
	//Object C is used to house the top card and then C is returned to caller.
	public Card Deal()
	{
		Card C = new Card();
		C = Dealer.get(0);
		Dealer.remove(0);
		return C;
	}

	//gets the cards left in the dealer deck
	public int getCardsLeft()
	{
		return Dealer.size();
	}    

}
