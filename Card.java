public class Card {
    protected int suit, face, value;
    protected final static String[] suits = { "C", "D", "H", "S" }; // The 4 suits of a deck; Diamonds, Clubs, Hearts & Spades
    protected final static String[] faces = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" }; // The 13 faces of the cards, from Ace(smallest = 1)
                                                                                                                  // to King(largest = 13)
    protected final static int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };                       // The value assigned to a card of a rank

    Card() {
    }

    public Card(int suit, int face, int value) {
        this.suit = suit;
        this.face = face;
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public int getFace() {
        return face;
    }

    public int getValue() {
        return values[value];
    }

    @Override
    public String toString() {
        return suits[suit] + faces[face];
    }
}