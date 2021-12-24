public class Card 
{
    private int rank;
    private int suit;
	
	public int getRankValue()
	{
		int rankVal = rank + 1;
		//account for the face cards
		if (rankVal >= 10)
		{
			rankVal = 10;
		}
		return rankVal;
	}

	public int getSuitValue()
	{
		int suitVal = suit + 1;
		return suitVal;
	}

  public Card(int rank, int suit) 
	{
    this.rank = rank;
    this.suit = suit;
    }

	public Card()
	{
		rank = 0;
		suit = 0;
	}

	//returns and tells the player what card they have. Ex: Card(3, 2); is 3 of Diamonds.
	public String toString() 
	{
		//max rank index value of 12
 		String[] ranks = {"Ace", "2", "3", "4", "5", "6",
 		"7", "8", "9", "10", "Jack", "Queen", "King"};
		 //max suit index value of 3
 		String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
	 
	  String s = ranks[rank] + " of " + suits[suit];
  	return s;
	}
    
}
