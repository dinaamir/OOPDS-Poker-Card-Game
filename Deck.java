import java.util.*;

public class Deck extends Card {

	protected Stack<Card> deck;

	Deck() {
	}

	public void create() {
		deck = new Stack<Card>();

		for (int suit = 0; suit < 4; suit++) { // Because we have 4 suits in total
			for (int face = 0; face < 13; face++) { // Because we have 13 faces in total
				if (face == 0)
					deck.push(new Card(suit, face, 1));
				else if (face == 1)
					deck.push(new Card(suit, face, 2));
				else if (face == 2)
					deck.push(new Card(suit, face, 3));
				else if (face == 3)
					deck.push(new Card(suit, face, 4));
				else if (face == 4)
					deck.push(new Card(suit, face, 5));
				else if (face == 5)
					deck.push(new Card(suit, face, 6));
				else if (face == 6)
					deck.push(new Card(suit, face, 7));
				else if (face == 7)
					deck.push(new Card(suit, face, 8));
				else if (face == 8)
					deck.push(new Card(suit, face, 9));
				else if (face == 9)
					deck.push(new Card(suit, face, 10));
				else if (face == 10)
					deck.push(new Card(suit, face, 10));
				else if (face == 11)
					deck.push(new Card(suit, face, 10));
				else if (face == 12)
					deck.push(new Card(suit, face, 10));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public Card dealCard() {
		return deck.pop();
	}

	public static Stack<Card> removeCard(Stack<Card> deck) {
		Stack<Card> inHand = new Stack<>();
		for (int i = 4; i >= 0; i--) {
			inHand.push(deck.remove(i));
		}
		return inHand;
	}

	public static Stack<Card> removeOneCard(Stack<Card> deck) {
		Stack<Card> inHand = new Stack<>();
		inHand.push(deck.remove(0));
		return inHand;
	}
}

class CardCompareSuit implements Comparator<Card> {
	public int compare(Card o1, Card o2) {

		if ((o1.getSuit() < o2.getSuit())) {
			return -1;
		} else if ((o1.getSuit() == o2.getSuit())) {
			return 0;
		} else {
			return 1;
		}
	}
}

class CardCompareFace implements Comparator<Card> {
	public int compare(Card o1, Card o2) {

		if ((o1.getFace() < o2.getFace())) {
			return -1;
		} else if ((o1.getFace() == o2.getFace())) {
			return 0;
		} else {
			return 1;
		}
	}
}