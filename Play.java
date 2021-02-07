import java.io.IOException;
import java.util.*;

public class Play extends Player{
    static Player playerOne;
    static Player playerTwo;
    static Player playerThree;
    static String name;
    final static int SIZE = 52;
	private static Stack<Card> removeCard;

    public static void main (String[] Args) {
        int sumP1 = 0, sumP2 = 0, sumP3 = 0;
        Character next;

        //Scanner input
        Scanner input = new Scanner(System.in);

        // 
        Stack<Card> playerHand1 = new Stack<>();
		Stack<Card> playerHand2 = new Stack<>();
		Stack<Card> playerHand3 = new Stack<>();

        //
		Stack<Card> p1 = new Stack<>(); 
		Stack<Card> p2 = new Stack<>();
		Stack<Card> p3 = new Stack<>();

        //
		Stack<Card> inHandp1 = new Stack<>();
        Stack<Card> inHandp2 = new Stack<>();
        Stack<Card> inHandp3 = new Stack<>();
        //inHand.create();
        //inHand.shuffleCards();


        //create a deck of playing cards
		Deck deck = new Deck();
		deck.create();
		deck.shuffleCards();

        //Inserting the cards into each player's stack
		for(int i = 0; i < 18; i++) {
            p1.push(deck.dealCard());
        }    
        for(int i = 0; i < 17; i++)
            p2.push(deck.dealCard());
        
        for(int i = 0; i < 17; i++)
            p3.push(deck.dealCard());

        //Prompt player's name
        printPhase3();
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter player " + (i + 1) + " name: ");
            name = input.nextLine();
            if (i == 0)
                playerOne = new Player(name);
            else if (i == 1)
                playerTwo = new Player(name);
            else
                playerThree = new Player(name);
        }

        do {
            //Display each player's card on deck
            System.out.println("\nAvailable Cards:");
            System.out.println(playerOne.getName() + "\t: " + p1.toString().replace("[", "").replace("]", "").replace(",", "") );
            System.out.println(playerTwo.getName() + "\t: " + p2.toString().replace("[", "").replace("]", "").replace(",", "") );
            System.out.println(playerThree.getName() + "\t: " + p3.toString().replace("[", "").replace("]", "").replace(",", "") );

            System.out.println("\nPress S/s to shuffle\nOR\nPress C/c to continue");
            System.out.print(">>> ");
            next = input.nextLine().charAt(0);

            if (next == 'S' | next == 's') {
                Collections.shuffle(p1);        // shuffle cards on deck
                Collections.shuffle(p2);        // shuffle cards on deck
                Collections.shuffle(p3);        // shuffle cards on deck
            }
        } while(next == 'S' | next == 's');

        System.out.println("\n*** ROUND 1 ***");
        System.out.println("Cards at Hand:");

        //remove 5 cards from deck
        inHandp1 = removeCard(p1);
        inHandp2 = removeCard(p2);
        inHandp3 = removeCard(p3);

        // display 5 cards in hand
        System.out.println(playerOne.getName() + "\t: " + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = ");
        System.out.println(playerTwo.getName() + "\t: " + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = ");
        System.out.println(playerThree.getName() + "\t: " + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = ");

        // get point

    }
    public static void printPhase3() {
        System.out.println("\n******************");
        System.out.println("* 3-Player Phase *");
        System.out.println("******************");

    }
}
