import java.util.Scanner;

public class Start_Game {
	
	static int pointPlayer = 0;
	static int pointDealer = 0;
	static int countAsP = 0;
	static int countAsD = 0;
	static Card tempDealerCard1 = null;
	static Card tempDealerCard2 = null;
	static Card tempPlayerCard1 = null;
	static Card tempPlayerCard2 = null;
	
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		// like K, Q, 2, 3, etc.
		String[] ranks = new String[] {
				"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
		};
		
		// diamond, heart, etc.
		String[] suits = new String[] {
				"Diamond", "Heart", "Club", "Spade"
		};
		// point values
		int[] values = new int[] {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10
		};
		
		Deck deck = new Deck(ranks, suits, values);
		
		deck.shuffle();
		System.out.println("Dealer's Cards:");
		tempDealerCard1 = deck.deal();
		System.out.println(tempDealerCard1);
		tempDealerCard2 = deck.deal();
		System.out.println("-----Hidden Card-----");
		pointDealer = tempDealerCard1.pointValue() + tempDealerCard2.pointValue();
		
		System.out.println("Your Cards:");
		tempPlayerCard1 = deck.deal();
		System.out.println(tempPlayerCard1);
		tempPlayerCard2 = deck.deal();
		System.out.println(tempPlayerCard2);
		pointPlayer = tempPlayerCard1.pointValue() + tempPlayerCard2.pointValue();
		checkForWin(deck);
	}
	public static int checkForWin(Deck deck) {
		int turn = 0;
		if (pointPlayer == 21) {
			System.out.println("Blackjack! You win!");
			return 0;
		}
		else {
			if (tempPlayerCard1.rank() == "A") {
				countAsP++;
				if (pointPlayer + 10 == 21) {
					System.out.println("Blackjack! You win!");
					return 0;
				}
				else if (countAsP > 1) {
					if (pointPlayer + 20 == 21) {
						System.out.println("Blackjack! You win!");
						return 0;
					}
				}
			}
			else if (tempPlayerCard2.rank() == "A") {
				countAsP++;
				if (pointPlayer + 10 == 21) {
					System.out.println("Blackjack! You win!");
					return 0;
				}
				else if (countAsP > 1) {
					if (pointPlayer + 20 == 21) {
						System.out.println("Blackjack! You win!");
						return 0;
					}
				}
			}
			if (pointPlayer > 21) {
				System.out.println("Bust! You lose!");
				return 0;
			}
			else if (turn > 0) {
				System.out.println("Dealer's Hidden Card was " + tempDealerCard2);
				while (pointDealer < 21) {
					tempDealerCard1 = deck.deal();
					System.out.println("Dealer got : " + tempDealerCard1);
					pointDealer = pointDealer + tempDealerCard1.pointValue();
				}
				if (pointDealer == 21) {
					System.out.println("You lose!");
					return 0;
				}
				else if (pointDealer > 21) {
					System.out.println("You lose!");
					return 0;
				}
				else if (Math.abs(pointPlayer - 21) > Math.abs(pointDealer - 21)) {
					System.out.println("You lose!");
					return 0;
				}
				else {
					System.out.println("You win!");
					return 0;
				}
			}
			else {
				System.out.println("Hit or Stand?");
				System.out.println("Enter 'H' or 'S' or 'Q' (to quit)");
				String choice = in.nextLine();
				switch (choice) {
				case "H":
					tempPlayerCard1 = deck.deal();
					System.out.println("You got: " + tempPlayerCard1);
					pointPlayer = pointPlayer + tempPlayerCard1.pointValue();
					turn++;
					checkForWin(deck);
					break;
				case "S":
					turn++;
					checkForWinStand(deck);
					break;
				default:
					System.out.println("Please enter valid command.");
					break;
				}
			}
			return 0;
		}
	}
	public static int checkForWinStand(Deck deck) {
		System.out.println("Dealer's Hidden Card was " + tempDealerCard2);
		while (pointDealer < 21) {
			tempDealerCard1 = deck.deal();
			System.out.println("Dealer got : " + tempDealerCard1);
			pointDealer = pointDealer + tempDealerCard1.pointValue();
		}
		if (pointDealer == 21) {
			System.out.println("You lose!");
			return 0;
		}
		else if (pointDealer > 21) {
			System.out.println("You win!");
			return 0;
		}
		else if (Math.abs(pointPlayer - 21) > Math.abs(pointDealer - 21)) {
			System.out.println("You lose!");
			return 0;
		}
		else {
			System.out.println("You win!");
			return 0;
		}
	}
}
