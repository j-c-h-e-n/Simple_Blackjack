/*
*	James Chen
*	Mr. Cramer
*	Prin of Java G/T
*	June 10, 2021
* Really Basic Blackjack
*/

import java.util.Scanner;

/*
*	SIDE NOTE: my winLoseTie function displays each situation properly, but somewhere in Main makes the
*	final scores to be printed out multiple times.
*/



class Main 
{
	static int playerTotal = 0;
	static int dealerTotal = 0;
	static Deck newDeck = new Deck(); //Initial 52 cars already shuffled
	static Scanner input = new Scanner(System.in);
	static int playerBet;
	static int chipAmt = 50;

  public static void main(String[] args) 
	{
		final int dealerChoice = 16; //if dealer hand >= 16, dealer will hold. If not, dealer will hit.
		System.out.println("Ace is equal to 1");
		System.out.println("You currently have " +chipAmt+ " chips.");


		//Card newCard = new Card(6, 2); Card test
    //System.out.println(newCard.toString());
		//Deck newDeck = new Deck(); //Initial 52 cards already shuffled
		int playAgain = 0;
		do
		{
			//Betting
			do
			{
				System.out.println("How much do you want to bet? Minimum of 2, maximum of 500: ");
				playerBet = input.nextInt();
				if(playerBet > chipAmt)
				{
					System.out.println("Please don't bet more than what you have.");
				}
				else if(playerBet < 2 || playerBet > 500)
				{
					System.out.println("Please enter a valid amount.");
				}
			}while(playerBet < 2 || playerBet > 500 || playerBet > chipAmt);


			//Sets up a new round
			playerTotal = 0; //reset values with every new game
			dealerTotal = 0;
			int turnCounter = 2;
			firstDeals();

			int playerHitOrHold = 0; //0 to continue hits. 1 to hold
			int dealerHitOrHold = 0;

			//Consecutive rounds after the first cards have been dealt.
			do
			{
				if(turnCounter%2 == 0) //player turn
				{
					if (newDeck.getCardsLeft() > 0)
					{
						System.out.println("0 to hit, 1 to hold.");
						playerHitOrHold = input.nextInt();
						if(playerHitOrHold == 0)
						{
							Card playerCard = newDeck.Deal();
    					System.out.format("%s %s\n", "You got: ", playerCard.toString());
							playerTotal = playerTotal + playerCard.getRankValue(); //adds card value to player score
							System.out.format("%s%10s\n", "You","Dealer");
							System.out.format("%d%10s\n", playerTotal, "??");				
							//System.out.format("%d%10d\n", playerTotal, dealerTotal);		
							System.out.println("");
							if(playerTotal >= 21)
							{
								break;
							}
						}
					}
					else
					{
						System.out.println("There are no cards left!");
						break;
					}
					//System.out.println(newDeck.getCardsLeft());
					turnCounter++;
				}
				if (turnCounter%2 == 1) //dealer turn
				{
					if(dealerTotal < dealerChoice) //hit
					{
						if(newDeck.getCardsLeft() > 0)
						{
							Card dealerCard = newDeck.Deal();
							//System.out.println(dealerCard.toString());
							dealerTotal = dealerTotal + dealerCard.getRankValue();	
							System.out.format("%s%10s\n", "You","Dealer");
							System.out.format("%d%10s\n", playerTotal, "??");				
							//System.out.format("%d%10d\n", playerTotal, dealerTotal);
							System.out.println("");
							if(dealerTotal >= 21)
							{
								break;
							}				
						}
						else
						{
							System.out.println("There are no cards left!");
						}
					}		
					else
					{
						dealerHitOrHold = 1;
					}
					//System.out.println(newDeck.getCardsLeft());
					turnCounter++;
				}
			//System.out.format("%d%10d\n", playerHitOrHold, dealerHitOrHold);
			}while(playerHitOrHold == 0 || dealerHitOrHold == 0);
			System.out.println("\nFinal Scores:");
			System.out.format("%s%10s\n", "You","Dealer");
			System.out.format("%d%10d\n", playerTotal, dealerTotal);
			System.out.println(winLoseTie());
			System.out.println("You now have " +betResult()+ " chips.");

			if(chipAmt <= 0)
			{
			 	System.out.println("You're broke!");
			 	playAgain = 1;
			}
			else
			{
				System.out.println("0 to play again, 1 to quit");
				playAgain = input.nextInt();
			}
		
		}while(playAgain == 0);
  } 




	static public void firstDeals()
	{
		if (newDeck.getCardsLeft() > 4) //check if there's enough cards to start
		{
			for (int i = 0; i < 2; i++)
			{
				//player
				Card playerCard = newDeck.Deal();
    		System.out.println(playerCard.toString());
				playerTotal = playerTotal + playerCard.getRankValue();
				//dealer
				Card dealerCard = newDeck.Deal();
				if (i == 0)
				{
					System.out.format("Dealer's first card: %s\n", dealerCard.toString());
				}
				//System.out.println(dealerCard.toString());
				dealerTotal = dealerTotal + dealerCard.getRankValue();
			}
			System.out.format("%s%10s\n", "You","Dealer");
			System.out.format("%d%10s\n", playerTotal, "??");
			//System.out.format("%d%10d\n", playerTotal, dealerTotal);
			System.out.println("");
		}
	}


	static public String winLoseTie()
	{
		String s = "";
		//bust situations
		if (playerTotal > 21 || dealerTotal > 21)
		{
      if (playerTotal > 21)
			{
				s = "You bust, dealer wins.";
			}
			if (dealerTotal > 21)
		  {		
			  s = "Dealer bust, you win.";			
		  }
		}

		//non-bust situations + tie
		if (playerTotal > dealerTotal && playerTotal <= 21)
		{			
			s = "You win!";

			if(playerTotal == 21)
			{		
				s = "You got a Blackjack!";
			}
		}
		else if (playerTotal < dealerTotal && dealerTotal <= 21)
		{
			s = "Dealer wins!";
		
			if(dealerTotal == 21)
			{	
				s = "Dealer got a Blackjack!";
			}
		}		
		
		else if (playerTotal == dealerTotal)
		{			
			s = "Tie.";
		}
		return s;
	}

	static public int betResult()
	{
		if (winLoseTie() == "You win!" || winLoseTie() == "You got a Blackjack!" || winLoseTie() == "Dealer bust, you win.")
		{
			chipAmt = chipAmt + playerBet;
		}
		else if(winLoseTie() == "You bust, dealer wins." || winLoseTie() == "Dealer wins!" || winLoseTie() == "Dealer got a Blackjack!")
		{
			chipAmt = chipAmt - playerBet;
		}
		else if(winLoseTie() == "Tie.")
		{
			chipAmt = chipAmt;
		}
		return chipAmt;
	}

}