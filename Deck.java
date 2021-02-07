import java.util.*;

public class Deck extends Card {
    protected Stack<Card> deck;                                 // create a stack named deck using the values from Card.java

    Deck() {}                          

    public void create() {                                      // create a deck of playing cards that will be used in the Game
        deck = new Stack<Card>();                               // instantiate a Stack deck

        for(int suit = 0; suit < 4; suit++) {                   //4 suits in total
            for(int face = 0; face < 13; face++) {              //13 faces in total
                if(face == 0) 
                    deck.push(new Card(suit, face, 1, 1));
                else if(face == 1) 
                    deck.push(new Card(suit, face, 2, 2));
                else if(face == 2) 
                    deck.push(new Card(suit, face, 3, 3));
                else if(face == 3) 
                    deck.push(new Card(suit, face, 4, 4));
                else if(face == 4) 
                    deck.push(new Card(suit, face, 5, 5));
                else if(face == 5) 
                    deck.push(new Card(suit, face, 6, 6));
                else if(face == 6) 
                    deck.push(new Card(suit, face, 7, 7));
                else if(face == 7) 
                    deck.push(new Card(suit, face, 8, 8));
                else if(face == 8) 
                    deck.push(new Card(suit, face, 9, 9));
                else if(face == 9) 
                    deck.push(new Card(suit, face, 10, 10));
                else if(face == 10) 
                    deck.push(new Card(suit, face, 11, 10));
                else if(face == 11) 
                    deck.push(new Card(suit, face, 12, 10));
                else if(face == 12) 
                    deck.push(new Card(suit, face, 13, 10));
            }
        }
    }
    //To shuffle the cards presented in the deck
    public void shuffleCards() {
        Collections.shuffle(deck);
    }

    public Card dealCard() {
		return deck.pop();
	}

    public static Stack<Card> removeCard(Stack<Card> deck) {
        Stack<Card> inHand = new Stack<>(); ;
        for(int i = 4; i >= 0; i--) {
            inHand.push(deck.remove(i));
        }
        return inHand;
    }
}
