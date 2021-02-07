public class Card {
    private int suit, face, rank;
    private static int value;
    private final static String[] suits = { "D", "C", "H", "S" };                   
    private final static String[] faces = { "A", "2", "3", "4", "5", "6", "7",      
                                            "8", "9", "10", "J", "Q", "K" };
    private final static int[] ranks = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private final static int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};           

    public Card() {}                                                               

    public Card(int suit, int face, int rank, int value) {                                    
        this.suit = suit;
        this.face = face;
        this.rank = rank;
        this.value = value;
    }
        public int getSuit() {
            return suit;
        }
        public int getFace() {
            return face;
        }
        public int getRank() {
            return ranks[rank];
        }
        public static int getValue() {
            return values[value];
        }
        @Override
        public String toString() {
            return suits[suit] + faces[face];
        }
    }
    