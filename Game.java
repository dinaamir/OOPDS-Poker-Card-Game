import java.util.*;

public class Game extends Deck {
    static Player playerOne, playerTwo, playerThree;
    static Player playerOnePhase2, playerTwoPhase2;
    static String name;

    public static void main(String[] Args) {
        int round = 1, pointP1 = 0, pointP2 = 0, pointP3 = 0, nextRound = 1;
        // int sumDiamonds = 0, sumClubs = 0, sumHeartsc = 0, sumSpades = 0;
        Character next;
        Scanner input = new Scanner(System.in);

        // create empty onDeck stacks each
        Stack<Card> onDeckp1 = new Stack<>();
        Stack<Card> onDeckp2 = new Stack<>();
        Stack<Card> onDeckp3 = new Stack<>();

        // create empty inHand stacks each
        Stack<Card> inHandp1 = new Stack<>();
        Stack<Card> inHandp2 = new Stack<>();
        Stack<Card> inHandp3 = new Stack<>();

        ////////////////////
        // 3-Player Phase //
        ////////////////////

        // create a deck of playing cards for 3-player phase
        Deck deck = new Deck();
        deck.create();
        deck.shuffle();

        for (int i = 0; i < 15; i++) {
            onDeckp1.push(deck.dealCard());
            onDeckp2.push(deck.dealCard());
            onDeckp3.push(deck.dealCard());
        }

        for (int i = 0; i < 3; i++)
            onDeckp1.push(deck.dealCard());

        for (int i = 0; i < 2; i++) {
            onDeckp2.push(deck.dealCard());
            onDeckp3.push(deck.dealCard());
        }

        System.out.println("\n+--------------------------+");
        System.out.println("|    Majority Rule Game    |");
        System.out.println("+--------------------------+");
        System.out.println("\nRules:");
        System.out.println("1. The game will start in 3-Player Phase then move on to 2-Player Phase.");
        System.out.println("2. All 52 cards will be distributed to each player at the beginning of each phase.");
        System.out.println("3. Players are allowed to shuffle their cards as many time as they wish at the beginning of each phase.");
        System.out.println("4. Showdown round, players are each dealt 5 cards.");
        System.out.println("5. Cards chosen from the suit that gives the highest total will calculate as the point.");
        System.out.println("6. Player with the highest score wins the game.");

        // Prompt player's name
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
            // Display each player's card on deck
            System.out.println("\nAvailable Cards:");
            System.out.println(playerOne.getName() + "\t: "
                    + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
            System.out.println(playerTwo.getName() + "\t: "
                    + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));
            System.out.println(playerThree.getName() + "\t: "
                    + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));
            System.out.println("\nPress S/s to shuffle\nOR\nPress C/c to continue");
            System.out.print(">>> ");
            next = input.nextLine().charAt(0);

            if (next == 'S' || next == 's') {
                Collections.shuffle(onDeckp1); // shuffle cards on deck
                Collections.shuffle(onDeckp2); // shuffle cards on deck
                Collections.shuffle(onDeckp3); // shuffle cards on deck
            }
        } while (next == 'S' || next == 's');

        do {
            int sumP1 = 0, sumP2 = 0, sumP3 = 0;
            int sumDiamondsP1 = 0, sumClubsP1 = 0, sumHeartsP1 = 0, sumSpadesP1 = 0;
            int sumDiamondsP2 = 0, sumClubsP2 = 0, sumHeartsP2 = 0, sumSpadesP2 = 0;
            int sumDiamondsP3 = 0, sumClubsP3 = 0, sumHeartsP3 = 0, sumSpadesP3 = 0;
            int countDiamondsP1 = 0, countClubsP1 = 0, countHeartsP1 = 0, countSpadesP1 = 0;
            int countDiamondsP2 = 0, countClubsP2 = 0, countHeartsP2 = 0, countSpadesP2 = 0;
            int countDiamondsP3 = 0, countClubsP3 = 0, countHeartsP3 = 0, countSpadesP3 = 0;

            // Display round
            System.out.println("\n*** ROUND " + round + " ***");
            System.out.println("Cards at Hand:");

            // remove 5 cards from deck
            inHandp1 = removeCard(onDeckp1);
            inHandp2 = removeCard(onDeckp2);
            inHandp3 = removeCard(onDeckp3);

            // Sort by face
            CardCompareFace cardCompareFace = new CardCompareFace();
            Collections.sort(inHandp1, cardCompareFace);
            Collections.sort(inHandp2, cardCompareFace);
            Collections.sort(inHandp3, cardCompareFace);

            // Sort by suit
            CardCompareSuit cardCompareSuit = new CardCompareSuit();
            Collections.sort(inHandp1, cardCompareSuit);
            Collections.sort(inHandp2, cardCompareSuit);
            Collections.sort(inHandp3, cardCompareSuit);

            // Getting the total point for player 1
            for (int i = 0; i < inHandp1.size(); i++) {
                if (inHandp1.get(i).getSuit() == 0) { // suit => "D"
                    countDiamondsP1++;
                    int tempD = inHandp1.get(i).getValue();
                    sumDiamondsP1 = sumDiamondsP1 + tempD;
                } else if (inHandp1.get(i).getSuit() == 1) { // suit => "C"
                    countClubsP1++;
                    int tempC = inHandp1.get(i).getValue();
                    sumClubsP1 = sumClubsP1 + tempC;
                } else if (inHandp1.get(i).getSuit() == 2) { // suit => "H"
                    countHeartsP1++;
                    int tempH = inHandp1.get(i).getValue();
                    sumHeartsP1 = sumHeartsP1 + tempH;
                } else if (inHandp1.get(i).getSuit() == 3) { // suit => "S"
                    countSpadesP1++;
                    int tempS = inHandp1.get(i).getValue();
                    sumSpadesP1 = sumSpadesP1 + tempS;
                }
            }
            // Getting the total point for player 2
            for (int i = 0; i < inHandp2.size(); i++) {
                if (inHandp2.get(i).getSuit() == 0) { // suit => "D"
                    countDiamondsP2++;
                    int tempD = inHandp2.get(i).getValue();
                    sumDiamondsP2 = sumDiamondsP2 + tempD;
                } else if (inHandp2.get(i).getSuit() == 1) { // suit => "C"
                    countClubsP2++;
                    int tempC = inHandp2.get(i).getValue();
                    sumClubsP2 = sumClubsP2 + tempC;
                } else if (inHandp2.get(i).getSuit() == 2) { // suit => "H"
                    countHeartsP2++;
                    int tempH = inHandp2.get(i).getValue();
                    sumHeartsP2 = sumHeartsP2 + tempH;
                } else if (inHandp2.get(i).getSuit() == 3) { // suit => "S"
                    countSpadesP2++;
                    int tempS = inHandp2.get(i).getValue();
                    sumSpadesP2 = sumSpadesP2 + tempS;
                }
            }
            // Getting the total point for player 3
            for (int i = 0; i < inHandp3.size(); i++) {
                if (inHandp3.get(i).getSuit() == 0) { // suit => "D"
                    countDiamondsP3++;
                    int tempD = inHandp3.get(i).getValue();
                    sumDiamondsP3 = sumDiamondsP3 + tempD;
                } else if (inHandp3.get(i).getSuit() == 1) { // suit => "C"
                    countClubsP3++;
                    int tempC = inHandp3.get(i).getValue();
                    sumClubsP3 = sumClubsP3 + tempC;
                } else if (inHandp3.get(i).getSuit() == 2) { // suit => "H"
                    countHeartsP3++;
                    int tempH = inHandp3.get(i).getValue();
                    sumHeartsP3 = sumHeartsP3 + tempH;
                } else if (inHandp3.get(i).getSuit() == 3) { // suit => "S"
                    countSpadesP3++;
                    int tempS = inHandp3.get(i).getValue();
                    sumSpadesP3 = sumSpadesP3 + tempS;
                }
            }

            // to choose majority suits for sumP1
            if (countDiamondsP1 > countClubsP1 && countDiamondsP1 > countHeartsP1 && countDiamondsP1 > countSpadesP1)
                sumP1 = sumP1 + sumDiamondsP1;
            else if (countClubsP1 > countDiamondsP1 && countClubsP1 > countHeartsP1 && countClubsP1 > countSpadesP1)
                sumP1 = sumP1 + sumClubsP1;
            else if (countHeartsP1 > countDiamondsP1 && countHeartsP1 > countClubsP1 && countHeartsP1 > countSpadesP1)
                sumP1 = sumP1 + sumHeartsP1;
            else if (countSpadesP1 > countDiamondsP1 && countSpadesP1 > countClubsP1 && countSpadesP1 > countHeartsP1)
                sumP1 = sumP1 + sumSpadesP1;
            else if (countDiamondsP1 == countClubsP1 && countDiamondsP1 > countHeartsP1
                    && countDiamondsP1 > countSpadesP1 && countClubsP1 > countHeartsP1
                    && countClubsP1 > countSpadesP1) {
                if (sumDiamondsP1 > sumClubsP1)
                    sumP1 = sumP1 + sumDiamondsP1;
                else
                    sumP1 = sumP1 + sumClubsP1;
            } else if (countDiamondsP1 == countHeartsP1 && countDiamondsP1 > countClubsP1
                    && countDiamondsP1 > countSpadesP1 && countHeartsP1 > countClubsP1
                    && countHeartsP1 > countSpadesP1) {
                if (sumDiamondsP1 > sumHeartsP1)
                    sumP1 = sumP1 + sumDiamondsP1;
                else
                    sumP1 = sumP1 + sumHeartsP1;
            } else if (countDiamondsP1 == countSpadesP1 && countDiamondsP1 > countClubsP1
                    && countDiamondsP1 > countHeartsP1 && countSpadesP1 > countClubsP1
                    && countSpadesP1 > countHeartsP1) {
                if (sumDiamondsP1 > sumSpadesP1)
                    sumP1 = sumP1 + sumDiamondsP1;
                else
                    sumP1 = sumP1 + sumSpadesP1;
            } else if (countHeartsP1 == countClubsP1 && countHeartsP1 > countDiamondsP1 && countHeartsP1 > countSpadesP1
                    && countClubsP1 > countDiamondsP1 && countClubsP1 > countSpadesP1) {
                if (sumHeartsP1 > sumClubsP1)
                    sumP1 = sumP1 + sumHeartsP1;
                else
                    sumP1 = sumP1 + sumClubsP1;
            } else if (countHeartsP1 == countSpadesP1 && countHeartsP1 > countDiamondsP1 && countHeartsP1 > countClubsP1
                    && countSpadesP1 > countDiamondsP1 && countSpadesP1 > countClubsP1) {
                if (sumHeartsP1 > sumSpadesP1)
                    sumP1 = sumP1 + sumHeartsP1;
                else
                    sumP1 = sumP1 + sumSpadesP1;
            } else if (countClubsP1 == countSpadesP1 && countClubsP1 > countDiamondsP1 && countClubsP1 > countHeartsP1
                    && countSpadesP1 > countDiamondsP1 && countSpadesP1 > countHeartsP1) {
                if (sumClubsP1 > sumSpadesP1)
                    sumP1 = sumP1 + sumClubsP1;
                else
                    sumP1 = sumP1 + sumSpadesP1;
            }

            // to choose majority suits for sumP2
            if (countDiamondsP2 > countClubsP2 && countDiamondsP2 > countHeartsP2 && countDiamondsP2 > countSpadesP2)
                sumP2 = sumP2 + sumDiamondsP2;
            else if (countClubsP2 > countDiamondsP2 && countClubsP2 > countHeartsP2 && countClubsP2 > countSpadesP2)
                sumP2 = sumP2 + sumClubsP2;
            else if (countHeartsP2 > countDiamondsP2 && countHeartsP2 > countClubsP2 && countHeartsP2 > countSpadesP2)
                sumP2 = sumP2 + sumHeartsP2;
            else if (countSpadesP2 > countDiamondsP2 && countSpadesP2 > countClubsP2 && countSpadesP2 > countHeartsP2)
                sumP2 = sumP2 + sumSpadesP2;
            else if (countDiamondsP2 == countClubsP2 && countDiamondsP2 > countHeartsP2
                    && countDiamondsP2 > countSpadesP2 && countClubsP2 > countHeartsP2
                    && countClubsP2 > countSpadesP2) {
                if (sumDiamondsP2 > sumClubsP2)
                    sumP2 = sumP2 + sumDiamondsP2;
                else
                    sumP2 = sumP2 + sumClubsP2;
            } else if (countDiamondsP2 == countHeartsP2 && countDiamondsP2 > countClubsP2
                    && countDiamondsP2 > countSpadesP2 && countHeartsP2 > countClubsP2
                    && countHeartsP2 > countSpadesP2) {
                if (sumDiamondsP2 > sumHeartsP2)
                    sumP2 = sumP2 + sumDiamondsP2;
                else
                    sumP2 = sumP2 + sumHeartsP2;
            } else if (countDiamondsP2 == countSpadesP2 && countDiamondsP2 > countClubsP2
                    && countDiamondsP2 > countHeartsP2 && countSpadesP2 > countClubsP2
                    && countSpadesP2 > countHeartsP2) {
                if (sumDiamondsP2 > sumSpadesP2)
                    sumP2 = sumP2 + sumDiamondsP2;
                else
                    sumP2 = sumP2 + sumSpadesP2;
            } else if (countHeartsP2 == countClubsP2 && countHeartsP2 > countDiamondsP2 && countHeartsP2 > countSpadesP2
                    && countClubsP2 > countDiamondsP2 && countClubsP2 > countSpadesP2) {
                if (sumHeartsP2 > sumClubsP2)
                    sumP2 = sumP2 + sumHeartsP2;
                else
                    sumP2 = sumP2 + sumClubsP2;
            } else if (countHeartsP2 == countSpadesP2 && countHeartsP2 > countDiamondsP2 && countHeartsP2 > countClubsP2
                    && countSpadesP2 > countDiamondsP2 && countSpadesP2 > countClubsP2) {
                if (sumHeartsP2 > sumSpadesP2)
                    sumP2 = sumP2 + sumHeartsP2;
                else
                    sumP2 = sumP2 + sumSpadesP2;
            } else if (countClubsP2 == countSpadesP2 && countClubsP2 > countDiamondsP2 && countClubsP2 > countHeartsP2
                    && countSpadesP2 > countDiamondsP2 && countSpadesP2 > countHeartsP2) {
                if (sumClubsP2 > sumSpadesP2)
                    sumP2 = sumP2 + sumClubsP2;
                else
                    sumP2 = sumP2 + sumSpadesP2;
            }

            // to choose majority suits for sumP3
            if (countDiamondsP3 > countClubsP3 && countDiamondsP3 > countHeartsP3 && countDiamondsP3 > countSpadesP3)
                sumP3 = sumP3 + sumDiamondsP3;
            else if (countClubsP3 > countDiamondsP3 && countClubsP3 > countHeartsP3 && countClubsP3 > countSpadesP3)
                sumP3 = sumP3 + sumClubsP3;
            else if (countHeartsP3 > countDiamondsP3 && countHeartsP3 > countClubsP3 && countHeartsP3 > countSpadesP3)
                sumP3 = sumP3 + sumHeartsP3;
            else if (countSpadesP3 > countDiamondsP3 && countSpadesP3 > countClubsP3 && countSpadesP3 > countHeartsP3)
                sumP3 = sumP3 + sumSpadesP3;
            else if (countDiamondsP3 == countClubsP3 && countDiamondsP3 > countHeartsP3
                    && countDiamondsP3 > countSpadesP3 && countClubsP3 > countHeartsP3
                    && countClubsP3 > countSpadesP3) {
                if (sumDiamondsP3 > sumClubsP3)
                    sumP3 = sumP3 + sumDiamondsP3;
                else
                    sumP3 = sumP3 + sumClubsP3;
            } else if (countDiamondsP3 == countHeartsP3 && countDiamondsP3 > countClubsP3
                    && countDiamondsP3 > countSpadesP3 && countHeartsP3 > countClubsP3
                    && countHeartsP3 > countSpadesP3) {
                if (sumDiamondsP3 > sumHeartsP3)
                    sumP3 = sumP3 + sumDiamondsP3;
                else
                    sumP3 = sumP3 + sumHeartsP3;
            } else if (countDiamondsP3 == countSpadesP3 && countDiamondsP3 > countClubsP3
                    && countDiamondsP3 > countHeartsP3 && countSpadesP3 > countClubsP3
                    && countSpadesP3 > countHeartsP3) {
                if (sumDiamondsP3 > sumSpadesP3)
                    sumP3 = sumP3 + sumDiamondsP3;
                else
                    sumP3 = sumP3 + sumSpadesP3;
            } else if (countHeartsP3 == countClubsP3 && countHeartsP3 > countDiamondsP3 && countHeartsP3 > countSpadesP3
                    && countClubsP3 > countDiamondsP3 && countClubsP3 > countSpadesP3) {
                if (sumHeartsP3 > sumClubsP3)
                    sumP3 = sumP3 + sumHeartsP3;
                else
                    sumP3 = sumP3 + sumClubsP3;
            } else if (countHeartsP3 == countSpadesP3 && countHeartsP3 > countDiamondsP3 && countHeartsP3 > countClubsP3
                    && countSpadesP3 > countDiamondsP3 && countSpadesP3 > countClubsP3) {
                if (sumHeartsP3 > sumSpadesP3)
                    sumP3 = sumP3 + sumHeartsP3;
                else
                    sumP3 = sumP3 + sumSpadesP3;
            } else if (countClubsP3 == countSpadesP3 && countClubsP3 > countDiamondsP3 && countClubsP3 > countHeartsP3
                    && countSpadesP3 > countDiamondsP3 && countSpadesP3 > countHeartsP3) {
                if (sumClubsP3 > sumSpadesP3)
                    sumP3 = sumP3 + sumClubsP3;
                else
                    sumP3 = sumP3 + sumSpadesP3;
            }

            // to choose winner
            if (sumP1 > sumP2 && sumP1 > sumP3) {
                // display 5 cards in hand
                System.out.println(playerOne.getName() + "\t: "
                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP1 + "   | WIN");
                System.out.println(playerTwo.getName() + "\t: "
                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP2);
                System.out.println(playerThree.getName() + "\t: "
                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP3);
            } else if (sumP2 > sumP1 && sumP2 > sumP3) {
                // display 5 cards in hand
                System.out.println(playerOne.getName() + "\t: "
                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP1);
                System.out.println(playerTwo.getName() + "\t: "
                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP2 + "   | WIN");
                System.out.println(playerThree.getName() + "\t: "
                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP3);
            } else if (sumP3 > sumP1 && sumP3 > sumP2) {
                // display 5 cards in hand
                System.out.println(playerOne.getName() + "\t: "
                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP1);
                System.out.println(playerTwo.getName() + "\t: "
                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP2);
                System.out.println(playerThree.getName() + "\t: "
                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP3 + "   | WIN");
            } else if (sumP1 == sumP2 && sumP1 > sumP3 && sumP2 > sumP3) {
                // display 5 cards in hand
                System.out.println(playerOne.getName() + "\t: "
                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP1 + "   | WIN");
                System.out.println(playerTwo.getName() + "\t: "
                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP2 + "   | WIN");
                System.out.println(playerThree.getName() + "\t: "
                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP3);
            } else if (sumP1 == sumP3 && sumP1 > sumP2 && sumP3 > sumP2) {
                // display 5 cards in hand
                System.out.println(playerOne.getName() + "\t: "
                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP1 + "   | WIN");
                System.out.println(playerTwo.getName() + "\t: "
                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP2);
                System.out.println(playerThree.getName() + "\t: "
                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP3 + "   | WIN");
            } else if (sumP2 == sumP3 && sumP2 > sumP1 && sumP3 > sumP1) {
                // display 5 cards in hand
                System.out.println(playerOne.getName() + "\t: "
                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP1);
                System.out.println(playerTwo.getName() + "\t: "
                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP2 + "   | WIN");
                System.out.println(playerThree.getName() + "\t: "
                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                        + sumP3 + "   | WIN");
            }

            // Choose the winner for the round
            if (sumP1 > sumP2 && sumP1 > sumP3)
                pointP1 = pointP1 + sumP1;
            else if (sumP2 > sumP1 && sumP2 > sumP3)
                pointP2 = pointP2 + sumP2;
            else if (sumP3 > sumP1 && sumP3 > sumP2)
                pointP3 = pointP3 + sumP3;
            else if (sumP1 == sumP2 && sumP1 > sumP3 && sumP2 > sumP3) {
                pointP1 = pointP1 + sumP1;
                pointP2 = pointP2 + sumP2;
            } else if (sumP1 == sumP3 && sumP1 > sumP2 && sumP3 > sumP2) {
                pointP1 = pointP1 + sumP1;
                pointP3 = pointP3 + sumP3;
            } else if (sumP2 == sumP3 && sumP2 > sumP1 && sumP3 > sumP1) {
                pointP2 = pointP2 + sumP2;
                pointP3 = pointP3 + sumP3;
            }

            // print score for each player
            System.out.println("\nScore :");
            System.out.println(playerOne.getName() + "\t: " + pointP1);
            System.out.println(playerTwo.getName() + "\t: " + pointP2);
            System.out.println(playerThree.getName() + "\t: " + pointP3);

            // Remaining cards
            System.out.println("\nAvailable Cards:");
            System.out.println(playerOne.getName() + "\t: "
                    + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
            System.out.println(playerTwo.getName() + "\t: "
                    + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));
            System.out.println(playerThree.getName() + "\t: "
                    + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));

            round++;

            // Conditions to go to 2-Player Phase
            if (round <= 3) {
                System.out.println("\nPress ENTER to next round");
                input.nextLine();
            } else {
                if (pointP1 > pointP3 && pointP2 > pointP3) {
                    System.out.println("\n**** " + playerOne.getName() + " and " + playerTwo.getName()
                            + " proceed to 2-Player Phase ****");
                    playerOnePhase2 = new Player(playerOne.getName());
                    playerTwoPhase2 = new Player(playerTwo.getName());
                    System.out.println("\nPress ENTER to continue to 2-Player");
                    input.nextLine();
                } else if (pointP1 > pointP2 && pointP3 > pointP2) {
                    System.out.println("\n**** " + playerOne.getName() + " and " + playerThree.getName()
                            + " proceed to 2-Player Phase ****");
                    playerOnePhase2 = new Player(playerOne.getName());
                    playerTwoPhase2 = new Player(playerThree.getName());
                    System.out.println("\nPress ENTER to continue to 2-Player");
                    input.nextLine();
                } else if (pointP2 > pointP1 && pointP3 > pointP1) {
                    System.out.println("\n**** " + playerTwo.getName() + " and " + playerThree.getName()
                            + " proceed to 2-Player Phase ****");
                    playerOnePhase2 = new Player(playerTwo.getName());
                    playerTwoPhase2 = new Player(playerThree.getName());
                    System.out.println("\nPress ENTER to continue to 2-Player");
                    input.nextLine();
                } else if (pointP1 == pointP2 && pointP3 > pointP1 && pointP3 > pointP2) { // if P3 already won, decide
                                                                                           // whether P1 or P2 to go to
                                                                                           // next round
                    sumP1 = 0;
                    sumP2 = 0;
                    inHandp1 = removeOneCard(onDeckp1);
                    inHandp2 = removeOneCard(onDeckp2);

                    // Sort by face
                    Collections.sort(inHandp1, cardCompareFace);
                    Collections.sort(inHandp2, cardCompareFace);

                    // Sort by suit
                    Collections.sort(inHandp1, cardCompareSuit);
                    Collections.sort(inHandp2, cardCompareSuit);

                    System.out.println("\n*** EXTRA ROUND ***");
                    int temp1 = inHandp1.get(0).getValue();
                    System.out.println("\nReveal 1 card from Player 1 : " + temp1);
                    sumP1 = sumP1 + temp1;
                    int temp2 = inHandp2.get(0).getValue();
                    System.out.println("Reveal 1 card from Player 2 : " + temp2 + "\n");
                    sumP2 = sumP2 + temp2;

                    if (sumP1 > sumP2) {
                        System.out.println(playerOne.getName() + "\t: "
                                + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP1 + "   | WIN");
                        System.out.println(playerTwo.getName() + "\t: "
                                + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP2);
                    } else {
                        System.out.println(playerOne.getName() + "\t: "
                                + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP1);
                        System.out.println(playerTwo.getName() + "\t: "
                                + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP2 + "   | WIN");
                    }

                    pointP1 = pointP1 + sumP1; // add points to existing score
                    pointP2 = pointP2 + sumP2; // add points to existing score

                    System.out.println("\nScore :");
                    System.out.println(playerOne.getName() + "\t: " + pointP1);
                    System.out.println(playerTwo.getName() + "\t: " + pointP2);

                    System.out.println("\nAvailable Cards:");
                    System.out.println(playerOne.getName() + "\t: "
                            + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
                    System.out.println(playerTwo.getName() + "\t: "
                            + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));

                    if (sumP1 > sumP2) {
                        System.out.println("\n**** " + playerOne.getName() + " and " + playerThree.getName()
                                + " proceed to 2-Player Phase ****");
                        playerOnePhase2 = new Player(playerOne.getName());
                        playerTwoPhase2 = new Player(playerThree.getName());
                        System.out.println("\nPress ENTER to continue to 2-Player");
                        input.nextLine();
                    } else if (sumP2 > sumP1) {
                        System.out.println("\n**** " + playerTwo.getName() + " and " + playerThree.getName()
                                + " proceed to 2-Player Phase ****");
                        playerOnePhase2 = new Player(playerTwo.getName());
                        playerTwoPhase2 = new Player(playerThree.getName());
                        System.out.println("\nPress ENTER to continue to 2-Player");
                        input.nextLine();
                    }
                } else if (pointP1 == pointP3 && pointP2 > pointP1 && pointP2 > pointP3) { // if P2 already won, decide
                                                                                           // whether P1 or P3 to go to
                                                                                           // next round
                    sumP1 = 0;
                    sumP3 = 0;
                    inHandp1 = removeOneCard(onDeckp1);
                    inHandp3 = removeOneCard(onDeckp3);

                    // Sort by face
                    Collections.sort(inHandp1, cardCompareFace);
                    Collections.sort(inHandp3, cardCompareFace);

                    // Sort by suit
                    Collections.sort(inHandp1, cardCompareSuit);
                    Collections.sort(inHandp3, cardCompareSuit);

                    System.out.println("\n*** EXTRA ROUND ***");
                    int temp1 = inHandp1.get(0).getValue();
                    System.out.println("\nReveal 1 card from Player 1 : " + temp1);
                    sumP1 = sumP1 + temp1;
                    int temp3 = inHandp3.get(0).getValue();
                    System.out.println("Reveal 1 card from Player 3 : " + temp3 + "\n");
                    sumP3 = sumP3 + temp3;

                    if (sumP1 > sumP3) {
                        System.out.println(playerOne.getName() + "\t: "
                                + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP1 + "   | WIN");
                        System.out.println(playerThree.getName() + "\t: "
                                + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP3);
                    } else {
                        System.out.println(playerOne.getName() + "\t: "
                                + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP1);
                        System.out.println(playerThree.getName() + "\t: "
                                + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP3 + "   | WIN");
                    }

                    pointP1 = pointP1 + sumP1; // add points to existing score
                    pointP3 = pointP3 + sumP3; // add points to existing score

                    // print score for each player
                    System.out.println("\nScore :");
                    System.out.println(playerOne.getName() + "\t: " + pointP1);
                    System.out.println(playerThree.getName() + "\t: " + pointP3);

                    // Remaining Cards
                    System.out.println("\nAvailable Cards:");
                    System.out.println(playerOne.getName() + "\t: "
                            + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
                    System.out.println(playerThree.getName() + "\t: "
                            + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));

                    if (sumP1 > sumP3) {
                        System.out.println("\n**** " + playerOne.getName() + " and " + playerTwo.getName()
                                + " proceed to 2-Player Phase ****");
                        playerOnePhase2 = new Player(playerOne.getName());
                        playerTwoPhase2 = new Player(playerTwo.getName());
                        System.out.println("\nPress ENTER to continue to 2-Player");
                        input.nextLine();
                    } else if (sumP3 > sumP1) {
                        System.out.println("\n**** " + playerTwo.getName() + " and " + playerThree.getName()
                                + " proceed to 2-Player Phase ****");
                        playerOnePhase2 = new Player(playerTwo.getName());
                        playerTwoPhase2 = new Player(playerThree.getName());
                        System.out.println("\nPress ENTER to continue to 2-Player");
                        input.nextLine();
                    }
                } else if (pointP2 == pointP3 && pointP1 > pointP2 && pointP1 > pointP3) { // if P1 already won, decide
                                                                                           // whether P2 or P3 to go to
                                                                                           // next round
                    sumP2 = 0;
                    sumP3 = 0;
                    inHandp2 = removeOneCard(onDeckp2);
                    inHandp3 = removeOneCard(onDeckp3);

                    // Sort by face
                    Collections.sort(inHandp2, cardCompareFace);
                    Collections.sort(inHandp3, cardCompareFace);

                    // Sort by suit
                    Collections.sort(inHandp2, cardCompareSuit);
                    Collections.sort(inHandp3, cardCompareSuit);

                    System.out.println("\n*** EXTRA ROUND ***");
                    int temp2 = inHandp2.get(0).getValue();
                    System.out.println("\nReveal 1 card from Player 2 : " + temp2);
                    sumP2 = sumP2 + temp2;

                    int temp3 = inHandp3.get(0).getValue();
                    System.out.println("Reveal 1 card from Player 3 : " + temp2 + "\n");
                    sumP3 = sumP3 + temp3;

                    if (sumP2 > sumP3) {
                        System.out.println(playerTwo.getName() + "\t: "
                                + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP2 + "   | WIN");
                        System.out.println(playerThree.getName() + "\t: "
                                + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP3);
                    } else {
                        System.out.println(playerTwo.getName() + "\t: "
                                + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP2);
                        System.out.println(playerThree.getName() + "\t: "
                                + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "")
                                + "\t| Point = " + sumP3 + "   | WIN");
                    }

                    pointP2 = pointP2 + sumP2; // add points to existing score
                    pointP3 = pointP3 + sumP3; // add points to existing score

                    System.out.println("\nScore :");
                    System.out.println(playerTwo.getName() + "\t: " + pointP2);
                    System.out.println(playerThree.getName() + "\t: " + pointP3);

                    System.out.println("\nAvailable Cards:");
                    System.out.println(playerTwo.getName() + "\t: "
                            + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));
                    System.out.println(playerThree.getName() + "\t: "
                            + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));

                    if (sumP2 > sumP3) {
                        System.out.println("\n**** " + playerOne.getName() + " and " + playerTwo.getName()
                                + " proceed to 2-Player Phase ****");
                        playerOnePhase2 = new Player(playerOne.getName());
                        playerTwoPhase2 = new Player(playerTwo.getName());
                        System.out.println("\nPress ENTER to continue to 2-Player");
                        input.nextLine();
                    } else if (sumP3 > sumP1) {
                        System.out.println("\n**** " + playerOne.getName() + " and " + playerThree.getName()
                                + " proceed to 2-Player Phase ****");
                        playerOnePhase2 = new Player(playerOne.getName());
                        playerTwoPhase2 = new Player(playerThree.getName());
                        System.out.println("\nPress ENTER to continue to 2-Player");
                        input.nextLine();
                    }
                }
            }
        } while (round <= 3);

        //////////////////// 1. P1 & P2
        // 2-Player Phase // 2. P1 & P3
        //////////////////// 3. P2 & P3

        printPhase2();
        onDeckp1.clear();
        onDeckp2.clear();
        onDeckp3.clear();
        inHandp1.clear();
        inHandp2.clear();
        inHandp3.clear();

        // create a deck of playing cards for 2-player phase
        Deck deck2 = new Deck();
        deck2.create();
        deck2.shuffle();

        // 1. P1 & P2
        if (playerOnePhase2.getName().equals(playerOne.getName())
                && playerTwoPhase2.getName().equals(playerTwo.getName())) {
            for (int i = 0; i < 25; i++) {
                onDeckp1.push(deck2.dealCard());
                onDeckp2.push(deck2.dealCard());
            }
            onDeckp1.push(deck2.dealCard());
            onDeckp2.push(deck2.dealCard());

            do {
                // Display each player's card on deck
                System.out.println("\nAvailable Cards:");
                System.out.println(playerOne.getName() + "\t: "
                        + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
                System.out.println(playerTwo.getName() + "\t: "
                        + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));
                System.out.println("\nPress S/s to shuffle\nOR\nPress C/c to continue");
                System.out.print(">>> ");
                next = input.nextLine().charAt(0);

                if (next == 'S' || next == 's') {
                    Collections.shuffle(onDeckp1); // shuffle cards on deck
                    Collections.shuffle(onDeckp2); // shuffle cards on deck
                }
            } while (next == 'S' || next == 's');

            do {
                int sumP1 = 0, sumP2 = 0;
                int sumDiamondsP1 = 0, sumClubsP1 = 0, sumHeartsP1 = 0, sumSpadesP1 = 0;
                int sumDiamondsP2 = 0, sumClubsP2 = 0, sumHeartsP2 = 0, sumSpadesP2 = 0;
                int countDiamondsP1 = 0, countClubsP1 = 0, countHeartsP1 = 0, countSpadesP1 = 0;
                int countDiamondsP2 = 0, countClubsP2 = 0, countHeartsP2 = 0, countSpadesP2 = 0;

                System.out.println("\n*** ROUND " + nextRound + " ***");
                System.out.println("Cards at Hand:");

                // remove 5 cards from deck
                inHandp1 = removeCard(onDeckp1);
                inHandp2 = removeCard(onDeckp2);

                // Sort by face
                CardCompareFace cardCompareFace = new CardCompareFace();
                Collections.sort(inHandp1, cardCompareFace);
                Collections.sort(inHandp2, cardCompareFace);

                // Sort by suit
                CardCompareSuit cardCompareSuit = new CardCompareSuit();
                Collections.sort(inHandp1, cardCompareSuit);
                Collections.sort(inHandp2, cardCompareSuit);

                // Getting the total point for player 1
                for (int i = 0; i < inHandp1.size(); i++) {
                    if (inHandp1.get(i).getSuit() == 0) { // suit => "D"
                        countDiamondsP1++;
                        int tempD = inHandp1.get(i).getValue();
                        sumDiamondsP1 = sumDiamondsP1 + tempD;
                    } else if (inHandp1.get(i).getSuit() == 1) { // suit => "C"
                        countClubsP1++;
                        int tempC = inHandp1.get(i).getValue();
                        sumClubsP1 = sumClubsP1 + tempC;
                    } else if (inHandp1.get(i).getSuit() == 2) { // suit => "H"
                        countHeartsP1++;
                        int tempH = inHandp1.get(i).getValue();
                        sumHeartsP1 = sumHeartsP1 + tempH;
                    } else if (inHandp1.get(i).getSuit() == 3) { // suit => "S"
                        countSpadesP1++;
                        int tempS = inHandp1.get(i).getValue();
                        sumSpadesP1 = sumSpadesP1 + tempS;
                    }
                }
                // Getting the total point for player 2
                for (int i = 0; i < inHandp2.size(); i++) {
                    if (inHandp2.get(i).getSuit() == 0) { // suit => "D"
                        countDiamondsP2++;
                        int tempD = inHandp2.get(i).getValue();
                        sumDiamondsP2 = sumDiamondsP2 + tempD;
                    } else if (inHandp2.get(i).getSuit() == 1) { // suit => "C"
                        countClubsP2++;
                        int tempC = inHandp2.get(i).getValue();
                        sumClubsP2 = sumClubsP2 + tempC;
                    } else if (inHandp2.get(i).getSuit() == 2) { // suit => "H"
                        countHeartsP2++;
                        int tempH = inHandp2.get(i).getValue();
                        sumHeartsP2 = sumHeartsP2 + tempH;
                    } else if (inHandp2.get(i).getSuit() == 3) { // suit => "S"
                        countSpadesP2++;
                        int tempS = inHandp2.get(i).getValue();
                        sumSpadesP2 = sumSpadesP2 + tempS;
                    }
                }

                // to choose majority suits for sumP1
                if (countDiamondsP1 > countClubsP1 && countDiamondsP1 > countHeartsP1
                        && countDiamondsP1 > countSpadesP1)
                    sumP1 = sumP1 + sumDiamondsP1;
                else if (countClubsP1 > countDiamondsP1 && countClubsP1 > countHeartsP1 && countClubsP1 > countSpadesP1)
                    sumP1 = sumP1 + sumClubsP1;
                else if (countHeartsP1 > countDiamondsP1 && countHeartsP1 > countClubsP1
                        && countHeartsP1 > countSpadesP1)
                    sumP1 = sumP1 + sumHeartsP1;
                else if (countSpadesP1 > countDiamondsP1 && countSpadesP1 > countClubsP1
                        && countSpadesP1 > countHeartsP1)
                    sumP1 = sumP1 + sumSpadesP1;
                else if (countDiamondsP1 == countClubsP1 && countDiamondsP1 > countHeartsP1
                        && countDiamondsP1 > countSpadesP1 && countClubsP1 > countHeartsP1
                        && countClubsP1 > countSpadesP1) {
                    if (sumDiamondsP1 > sumClubsP1)
                        sumP1 = sumP1 + sumDiamondsP1;
                    else
                        sumP1 = sumP1 + sumClubsP1;
                } else if (countDiamondsP1 == countHeartsP1 && countDiamondsP1 > countClubsP1
                        && countDiamondsP1 > countSpadesP1 && countHeartsP1 > countClubsP1
                        && countHeartsP1 > countSpadesP1) {
                    if (sumDiamondsP1 > sumHeartsP1)
                        sumP1 = sumP1 + sumDiamondsP1;
                    else
                        sumP1 = sumP1 + sumHeartsP1;
                } else if (countDiamondsP1 == countSpadesP1 && countDiamondsP1 > countClubsP1
                        && countDiamondsP1 > countHeartsP1 && countSpadesP1 > countClubsP1
                        && countSpadesP1 > countHeartsP1) {
                    if (sumDiamondsP1 > sumSpadesP1)
                        sumP1 = sumP1 + sumDiamondsP1;
                    else
                        sumP1 = sumP1 + sumSpadesP1;
                } else if (countHeartsP1 == countClubsP1 && countHeartsP1 > countDiamondsP1
                        && countHeartsP1 > countSpadesP1 && countClubsP1 > countDiamondsP1
                        && countClubsP1 > countSpadesP1) {
                    if (sumHeartsP1 > sumClubsP1)
                        sumP1 = sumP1 + sumHeartsP1;
                    else
                        sumP1 = sumP1 + sumClubsP1;
                } else if (countHeartsP1 == countSpadesP1 && countHeartsP1 > countDiamondsP1
                        && countHeartsP1 > countClubsP1 && countSpadesP1 > countDiamondsP1
                        && countSpadesP1 > countClubsP1) {
                    if (sumHeartsP1 > sumSpadesP1)
                        sumP1 = sumP1 + sumHeartsP1;
                    else
                        sumP1 = sumP1 + sumSpadesP1;
                } else if (countClubsP1 == countSpadesP1 && countClubsP1 > countDiamondsP1
                        && countClubsP1 > countHeartsP1 && countSpadesP1 > countDiamondsP1
                        && countSpadesP1 > countHeartsP1) {
                    if (sumClubsP1 > sumSpadesP1)
                        sumP1 = sumP1 + sumClubsP1;
                    else
                        sumP1 = sumP1 + sumSpadesP1;
                }

                // to choose majority suits for sumP2
                if (countDiamondsP2 > countClubsP2 && countDiamondsP2 > countHeartsP2
                        && countDiamondsP2 > countSpadesP2)
                    sumP2 = sumP2 + sumDiamondsP2;
                else if (countClubsP2 > countDiamondsP2 && countClubsP2 > countHeartsP2 && countClubsP2 > countSpadesP2)
                    sumP2 = sumP2 + sumClubsP2;
                else if (countHeartsP2 > countDiamondsP2 && countHeartsP2 > countClubsP2
                        && countHeartsP2 > countSpadesP2)
                    sumP2 = sumP2 + sumHeartsP2;
                else if (countSpadesP2 > countDiamondsP2 && countSpadesP2 > countClubsP2
                        && countSpadesP2 > countHeartsP2)
                    sumP2 = sumP2 + sumSpadesP2;
                else if (countDiamondsP2 == countClubsP2 && countDiamondsP2 > countHeartsP2
                        && countDiamondsP2 > countSpadesP2 && countClubsP2 > countHeartsP2
                        && countClubsP2 > countSpadesP2) {
                    if (sumDiamondsP2 > sumClubsP2)
                        sumP2 = sumP2 + sumDiamondsP2;
                    else
                        sumP2 = sumP2 + sumClubsP2;
                } else if (countDiamondsP2 == countHeartsP2 && countDiamondsP2 > countClubsP2
                        && countDiamondsP2 > countSpadesP2 && countHeartsP2 > countClubsP2
                        && countHeartsP2 > countSpadesP2) {
                    if (sumDiamondsP2 > sumHeartsP2)
                        sumP2 = sumP2 + sumDiamondsP2;
                    else
                        sumP2 = sumP2 + sumHeartsP2;
                } else if (countDiamondsP2 == countSpadesP2 && countDiamondsP2 > countClubsP2
                        && countDiamondsP2 > countHeartsP2 && countSpadesP2 > countClubsP2
                        && countSpadesP2 > countHeartsP2) {
                    if (sumDiamondsP2 > sumSpadesP2)
                        sumP2 = sumP2 + sumDiamondsP2;
                    else
                        sumP2 = sumP2 + sumSpadesP2;
                } else if (countHeartsP2 == countClubsP2 && countHeartsP2 > countDiamondsP2
                        && countHeartsP2 > countSpadesP2 && countClubsP2 > countDiamondsP2
                        && countClubsP2 > countSpadesP2) {
                    if (sumHeartsP2 > sumClubsP2)
                        sumP2 = sumP2 + sumHeartsP2;
                    else
                        sumP2 = sumP2 + sumClubsP2;
                } else if (countHeartsP2 == countSpadesP2 && countHeartsP2 > countDiamondsP2
                        && countHeartsP2 > countClubsP2 && countSpadesP2 > countDiamondsP2
                        && countSpadesP2 > countClubsP2) {
                    if (sumHeartsP2 > sumSpadesP2)
                        sumP2 = sumP2 + sumHeartsP2;
                    else
                        sumP2 = sumP2 + sumSpadesP2;
                } else if (countClubsP2 == countSpadesP2 && countClubsP2 > countDiamondsP2
                        && countClubsP2 > countHeartsP2 && countSpadesP2 > countDiamondsP2
                        && countSpadesP2 > countHeartsP2) {
                    if (sumClubsP2 > sumSpadesP2)
                        sumP2 = sumP2 + sumClubsP2;
                    else
                        sumP2 = sumP2 + sumSpadesP2;
                }

                // display 5 cards in hand
                if (sumP1 > sumP2) {
                    System.out.println(playerOne.getName() + "\t: "
                            + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP1 + "   | WIN");
                    System.out.println(playerTwo.getName() + "\t: "
                            + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP2);
                } else if (sumP2 > sumP1) {
                    System.out.println(playerOne.getName() + "\t: "
                            + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP1);
                    System.out.println(playerTwo.getName() + "\t: "
                            + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP2 + "   | WIN");
                } else if (sumP1 == sumP2) {
                    System.out.println(playerOne.getName() + "\t: "
                            + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP1 + "   | WIN");
                    System.out.println(playerTwo.getName() + "\t: "
                            + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP2 + "   | WIN");
                }

                // Choose the winner for the round
                if (sumP1 > sumP2)
                    pointP1 = pointP1 + sumP1;
                else if (sumP2 > sumP1)
                    pointP2 = pointP2 + sumP2;
                else if (sumP1 == sumP2) {
                    pointP1 = pointP1 + sumP1;
                    pointP2 = pointP2 + sumP2;
                }

                // print score for each player
                System.out.println("\nScore :");
                System.out.println(playerOne.getName() + "\t: " + pointP1);
                System.out.println(playerTwo.getName() + "\t: " + pointP2);

                // Remaining cards
                System.out.println("\nAvailable Cards:");
                System.out.println(playerOne.getName() + "\t: "
                        + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
                System.out.println(playerTwo.getName() + "\t: "
                        + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));

                nextRound++;

                // conditions to find the winner
                if (nextRound <= 4) {
                    System.out.println("\nPress ENTER to next round");
                    input.nextLine();
                } else {
                    if (pointP1 > pointP2)
                        System.out.println("\n**** " + playerOne.getName() + " is the WINNER!! ****");
                    else if (pointP2 > pointP1)
                        System.out.println("\n**** " + playerTwo.getName() + " is the WINNER!! ****");
                    // tie 1
                    else if (pointP1 == pointP2) {
                        do {
                            sumP1 = 0;
                            sumP2 = 0;

                            inHandp1 = removeOneCard(onDeckp1);
                            inHandp2 = removeOneCard(onDeckp2);

                            System.out.println("\n*** EXTRA ROUND ***");
                            int temp1 = inHandp1.get(0).getValue();
                            System.out.println("\nReveal 1 card from Player 1 : " + temp1);
                            sumP1 = sumP1 + temp1;

                            int temp2 = inHandp2.get(0).getValue();
                            System.out.println("Reveal 1 card from Player 2 : " + temp2 + "\n");
                            sumP2 = sumP2 + temp2;

                            if (sumP1 > sumP2) {
                                System.out.println(playerOne.getName() + "\t: "
                                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP1 + "   | WIN");
                                System.out.println(playerTwo.getName() + "\t: "
                                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP2);
                            } else {
                                System.out.println(playerOne.getName() + "\t: "
                                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP1);
                                System.out.println(playerTwo.getName() + "\t: "
                                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP2 + "   | WIN");
                            }

                            pointP1 = pointP1 + sumP1; // add points to existing score
                            pointP2 = pointP2 + sumP2; // add points to existing score

                            System.out.println("\nScore :");
                            System.out.println(playerOne.getName() + "\t: " + pointP1);
                            System.out.println(playerTwo.getName() + "\t: " + pointP2);

                            System.out.println("\nAvailable Cards:");
                            System.out.println(playerOne.getName() + "\t: "
                                    + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
                            System.out.println(playerTwo.getName() + "\t: "
                                    + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));

                            if (sumP1 > sumP2)
                                System.out.println("\n**** " + playerOne.getName() + " is the WINNER!! ****");
                            else if (sumP2 > sumP1)
                                System.out.println("\n**** " + playerTwo.getName() + " is the WINNER!! ****");
                        } while (pointP1 == pointP2);
                    }
                }
            } while (nextRound <= 4);
        }
        // 1. P1 & P3
        else if (playerOnePhase2.getName().equals(playerOne.getName())
                && playerTwoPhase2.getName().equals(playerThree.getName())) {
            for (int i = 0; i < 25; i++) {
                onDeckp1.push(deck2.dealCard());
                onDeckp3.push(deck2.dealCard());
            }
            onDeckp1.push(deck2.dealCard());
            onDeckp3.push(deck2.dealCard());

            do {
                // Display each player's card on deck
                System.out.println("\nAvailable Cards:");
                System.out.println(playerOne.getName() + "\t: "
                        + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
                System.out.println(playerThree.getName() + "\t: "
                        + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));
                System.out.println("\nPress S/s to shuffle\nOR\nPress C/c to continue");
                System.out.print(">>> ");
                next = input.nextLine().charAt(0);

                if (next == 'S' || next == 's') {
                    Collections.shuffle(onDeckp1); // shuffle cards on deck
                    Collections.shuffle(onDeckp3); // shuffle cards on deck
                }
            } while (next == 'S' || next == 's');

            do {
                int sumP1 = 0, sumP3 = 0;
                int sumDiamondsP1 = 0, sumClubsP1 = 0, sumHeartsP1 = 0, sumSpadesP1 = 0;
                int sumDiamondsP3 = 0, sumClubsP3 = 0, sumHeartsP3 = 0, sumSpadesP3 = 0;
                int countDiamondsP1 = 0, countClubsP1 = 0, countHeartsP1 = 0, countSpadesP1 = 0;
                int countDiamondsP3 = 0, countClubsP3 = 0, countHeartsP3 = 0, countSpadesP3 = 0;

                System.out.println("\n*** ROUND " + nextRound + " ***");
                System.out.println("Cards at Hand:");

                // remove 5 cards from deck
                inHandp1 = removeCard(onDeckp1);
                inHandp3 = removeCard(onDeckp3);

                // Sort by face
                CardCompareFace cardCompareFace = new CardCompareFace();
                Collections.sort(inHandp1, cardCompareFace);
                Collections.sort(inHandp3, cardCompareFace);

                // Sort by suit
                CardCompareSuit cardCompareSuit = new CardCompareSuit();
                Collections.sort(inHandp1, cardCompareSuit);
                Collections.sort(inHandp3, cardCompareSuit);

                for (int i = 0; i < inHandp1.size(); i++) {
                    if (inHandp1.get(i).getSuit() == 0) { // suit => "D"
                        countDiamondsP1++;
                        int tempD = inHandp1.get(i).getValue();
                        sumDiamondsP1 = sumDiamondsP1 + tempD;
                    } else if (inHandp1.get(i).getSuit() == 1) { // suit => "C"
                        countClubsP1++;
                        int tempC = inHandp1.get(i).getValue();
                        sumClubsP1 = sumClubsP1 + tempC;
                    } else if (inHandp1.get(i).getSuit() == 2) { // suit => "H"
                        countHeartsP1++;
                        int tempH = inHandp1.get(i).getValue();
                        sumHeartsP1 = sumHeartsP1 + tempH;
                    } else if (inHandp1.get(i).getSuit() == 3) { // suit => "S"
                        countSpadesP1++;
                        int tempS = inHandp1.get(i).getValue();
                        sumSpadesP1 = sumSpadesP1 + tempS;
                    }
                }

                // Getting the total point for player 3
                for (int i = 0; i < inHandp3.size(); i++) {
                    if (inHandp3.get(i).getSuit() == 0) { // suit => "D"
                        countDiamondsP3++;
                        int tempD = inHandp3.get(i).getValue();
                        sumDiamondsP3 = sumDiamondsP3 + tempD;
                    } else if (inHandp3.get(i).getSuit() == 1) { // suit => "C"
                        countClubsP3++;
                        int tempC = inHandp3.get(i).getValue();
                        sumClubsP3 = sumClubsP3 + tempC;
                    } else if (inHandp3.get(i).getSuit() == 2) { // suit => "H"
                        countHeartsP3++;
                        int tempH = inHandp3.get(i).getValue();
                        sumHeartsP3 = sumHeartsP3 + tempH;
                    } else if (inHandp3.get(i).getSuit() == 3) { // suit => "S"
                        countSpadesP3++;
                        int tempS = inHandp3.get(i).getValue();
                        sumSpadesP3 = sumSpadesP3 + tempS;
                    }
                }

                // to choose majority suits for sumP1
                if (countDiamondsP1 > countClubsP1 && countDiamondsP1 > countHeartsP1
                        && countDiamondsP1 > countSpadesP1)
                    sumP1 = sumP1 + sumDiamondsP1;
                else if (countClubsP1 > countDiamondsP1 && countClubsP1 > countHeartsP1 && countClubsP1 > countSpadesP1)
                    sumP1 = sumP1 + sumClubsP1;
                else if (countHeartsP1 > countDiamondsP1 && countHeartsP1 > countClubsP1
                        && countHeartsP1 > countSpadesP1)
                    sumP1 = sumP1 + sumHeartsP1;
                else if (countSpadesP1 > countDiamondsP1 && countSpadesP1 > countClubsP1
                        && countSpadesP1 > countHeartsP1)
                    sumP1 = sumP1 + sumSpadesP1;
                else if (countDiamondsP1 == countClubsP1 && countDiamondsP1 > countHeartsP1
                        && countDiamondsP1 > countSpadesP1 && countClubsP1 > countHeartsP1
                        && countClubsP1 > countSpadesP1) {
                    if (sumDiamondsP1 > sumClubsP1)
                        sumP1 = sumP1 + sumDiamondsP1;
                    else
                        sumP1 = sumP1 + sumClubsP1;
                } else if (countDiamondsP1 == countHeartsP1 && countDiamondsP1 > countClubsP1
                        && countDiamondsP1 > countSpadesP1 && countHeartsP1 > countClubsP1
                        && countHeartsP1 > countSpadesP1) {
                    if (sumDiamondsP1 > sumHeartsP1)
                        sumP1 = sumP1 + sumDiamondsP1;
                    else
                        sumP1 = sumP1 + sumHeartsP1;
                } else if (countDiamondsP1 == countSpadesP1 && countDiamondsP1 > countClubsP1
                        && countDiamondsP1 > countHeartsP1 && countSpadesP1 > countClubsP1
                        && countSpadesP1 > countHeartsP1) {
                    if (sumDiamondsP1 > sumSpadesP1)
                        sumP1 = sumP1 + sumDiamondsP1;
                    else
                        sumP1 = sumP1 + sumSpadesP1;
                } else if (countHeartsP1 == countClubsP1 && countHeartsP1 > countDiamondsP1
                        && countHeartsP1 > countSpadesP1 && countClubsP1 > countDiamondsP1
                        && countClubsP1 > countSpadesP1) {
                    if (sumHeartsP1 > sumClubsP1)
                        sumP1 = sumP1 + sumHeartsP1;
                    else
                        sumP1 = sumP1 + sumClubsP1;
                } else if (countHeartsP1 == countSpadesP1 && countHeartsP1 > countDiamondsP1
                        && countHeartsP1 > countClubsP1 && countSpadesP1 > countDiamondsP1
                        && countSpadesP1 > countClubsP1) {
                    if (sumHeartsP1 > sumSpadesP1)
                        sumP1 = sumP1 + sumHeartsP1;
                    else
                        sumP1 = sumP1 + sumSpadesP1;
                } else if (countClubsP1 == countSpadesP1 && countClubsP1 > countDiamondsP1
                        && countClubsP1 > countHeartsP1 && countSpadesP1 > countDiamondsP1
                        && countSpadesP1 > countHeartsP1) {
                    if (sumClubsP1 > sumSpadesP1)
                        sumP1 = sumP1 + sumClubsP1;
                    else
                        sumP1 = sumP1 + sumSpadesP1;
                }

                // to choose majority suits for sumP3
                if (countDiamondsP3 > countClubsP3 && countDiamondsP3 > countHeartsP3
                        && countDiamondsP3 > countSpadesP3)
                    sumP3 = sumP3 + sumDiamondsP3;
                else if (countClubsP3 > countDiamondsP3 && countClubsP3 > countHeartsP3 && countClubsP3 > countSpadesP3)
                    sumP3 = sumP3 + sumClubsP3;
                else if (countHeartsP3 > countDiamondsP3 && countHeartsP3 > countClubsP3
                        && countHeartsP3 > countSpadesP3)
                    sumP3 = sumP3 + sumHeartsP3;
                else if (countSpadesP3 > countDiamondsP3 && countSpadesP3 > countClubsP3
                        && countSpadesP3 > countHeartsP3)
                    sumP3 = sumP3 + sumSpadesP3;
                else if (countDiamondsP3 == countClubsP3 && countDiamondsP3 > countHeartsP3
                        && countDiamondsP3 > countSpadesP3 && countClubsP3 > countHeartsP3
                        && countClubsP3 > countSpadesP3) {
                    if (sumDiamondsP3 > sumClubsP3)
                        sumP3 = sumP3 + sumDiamondsP3;
                    else
                        sumP3 = sumP3 + sumClubsP3;
                } else if (countDiamondsP3 == countHeartsP3 && countDiamondsP3 > countClubsP3
                        && countDiamondsP3 > countSpadesP3 && countHeartsP3 > countClubsP3
                        && countHeartsP3 > countSpadesP3) {
                    if (sumDiamondsP3 > sumHeartsP3)
                        sumP3 = sumP3 + sumDiamondsP3;
                    else
                        sumP3 = sumP3 + sumHeartsP3;
                } else if (countDiamondsP3 == countSpadesP3 && countDiamondsP3 > countClubsP3
                        && countDiamondsP3 > countHeartsP3 && countSpadesP3 > countClubsP3
                        && countSpadesP3 > countHeartsP3) {
                    if (sumDiamondsP3 > sumSpadesP3)
                        sumP3 = sumP3 + sumDiamondsP3;
                    else
                        sumP3 = sumP3 + sumSpadesP3;
                } else if (countHeartsP3 == countClubsP3 && countHeartsP3 > countDiamondsP3
                        && countHeartsP3 > countSpadesP3 && countClubsP3 > countDiamondsP3
                        && countClubsP3 > countSpadesP3) {
                    if (sumHeartsP3 > sumClubsP3)
                        sumP3 = sumP3 + sumHeartsP3;
                    else
                        sumP3 = sumP3 + sumClubsP3;
                } else if (countHeartsP3 == countSpadesP3 && countHeartsP3 > countDiamondsP3
                        && countHeartsP3 > countClubsP3 && countSpadesP3 > countDiamondsP3
                        && countSpadesP3 > countClubsP3) {
                    if (sumHeartsP3 > sumSpadesP3)
                        sumP3 = sumP3 + sumHeartsP3;
                    else
                        sumP3 = sumP3 + sumSpadesP3;
                } else if (countClubsP3 == countSpadesP3 && countClubsP3 > countDiamondsP3
                        && countClubsP3 > countHeartsP3 && countSpadesP3 > countDiamondsP3
                        && countSpadesP3 > countHeartsP3) {
                    if (sumClubsP3 > sumSpadesP3)
                        sumP3 = sumP3 + sumClubsP3;
                    else
                        sumP3 = sumP3 + sumSpadesP3;
                }

                // display 5 cards in hand
                if (sumP1 > sumP3) {
                    System.out.println(playerOne.getName() + "\t: "
                            + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP1 + "   | WIN");
                    System.out.println(playerThree.getName() + "\t: "
                            + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP3);
                } else if (sumP3 > sumP1) {
                    System.out.println(playerOne.getName() + "\t: "
                            + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP1);
                    System.out.println(playerThree.getName() + "\t: "
                            + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP3 + "   | WIN");
                } else if (sumP1 == sumP3) {
                    System.out.println(playerOne.getName() + "\t: "
                            + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP1 + "   | WIN");
                    System.out.println(playerThree.getName() + "\t: "
                            + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP3 + "   | WIN");
                }

                // Choose the winner for the round
                if (sumP1 > sumP3)
                    pointP1 = pointP1 + sumP1;
                else if (sumP3 > sumP1)
                    pointP3 = pointP3 + sumP3;
                else if (sumP1 == sumP3) {
                    pointP1 = pointP1 + sumP1;
                    pointP3 = pointP3 + sumP3;
                }

                // print score for each player
                System.out.println("\nScore :");
                System.out.println(playerOne.getName() + "\t: " + pointP1);
                System.out.println(playerThree.getName() + "\t: " + pointP3);

                // Remaining cards
                System.out.println("\nAvailable Cards:");
                System.out.println(playerOne.getName() + "\t: "
                        + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
                System.out.println(playerThree.getName() + "\t: "
                        + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));

                nextRound++;

                // condition to find the winner
                if (nextRound <= 4) {
                    System.out.println("\nPress ENTER to next round");
                    input.nextLine();
                } else {
                    if (pointP1 > pointP3)
                        System.out.println("\n**** " + playerOne.getName() + " is the WINNER!! ****");
                    else if (pointP3 > pointP1)
                        System.out.println("\n**** " + playerThree.getName() + " is the WINNER!! ****");
                    // tie 1
                    else if (pointP1 == pointP3) {
                        do {
                            sumP1 = 0;
                            sumP3 = 0;

                            inHandp1 = removeOneCard(onDeckp1);
                            inHandp3 = removeOneCard(onDeckp3);

                            System.out.println("\n*** EXTRA ROUND ***");
                            int temp1 = inHandp1.get(0).getValue();
                            System.out.println("\nReveal 1 card from Player 1 : " + temp1);
                            sumP1 = sumP1 + temp1;

                            int temp3 = inHandp3.get(0).getValue();
                            System.out.println("Reveal 1 card from Player 3 : " + temp3 + "\n");
                            sumP3 = sumP3 + temp3;

                            if (sumP1 > sumP3) {
                                System.out.println(playerOne.getName() + "\t: "
                                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP1 + "   | WIN");
                                System.out.println(playerThree.getName() + "\t: "
                                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP3);
                            } else {
                                System.out.println(playerOne.getName() + "\t: "
                                        + inHandp1.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP1);
                                System.out.println(playerThree.getName() + "\t: "
                                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP3 + "   | WIN");
                            }

                            pointP1 = pointP1 + sumP1; // add points to existing score
                            pointP3 = pointP3 + sumP3; // add points to existing score

                            System.out.println("\nScore :");
                            System.out.println(playerOne.getName() + "\t: " + pointP1);
                            System.out.println(playerThree.getName() + "\t: " + pointP3);

                            System.out.println("\nAvailable Cards:");
                            System.out.println(playerOne.getName() + "\t: "
                                    + onDeckp1.toString().replace("[", "").replace("]", "").replace(",", ""));
                            System.out.println(playerThree.getName() + "\t: "
                                    + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));

                            if (sumP1 > sumP3)
                                System.out.println("\n**** " + playerOne.getName() + " is the WINNER!! ****");
                            else if (sumP3 > sumP1)
                                System.out.println("\n**** " + playerThree.getName() + " is the WINNER!! ****");
                        } while (pointP1 == pointP3);
                    }
                }
            } while (nextRound <= 4);
        }
        // 3. P2 & P3
        else if (playerOnePhase2.getName().equals(playerTwo.getName())
                & playerTwoPhase2.getName().equals(playerThree.getName())) {
            for (int i = 0; i < 25; i++) {
                onDeckp2.push(deck2.dealCard());
                onDeckp3.push(deck2.dealCard());
            }
            onDeckp2.push(deck2.dealCard());
            onDeckp3.push(deck2.dealCard());

            do {
                // Display each player's card on deck
                System.out.println("\nAvailable Cards:");
                System.out.println(playerTwo.getName() + "\t: "
                        + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));
                System.out.println(playerThree.getName() + "\t: "
                        + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));
                System.out.println("\nPress S/s to shuffle\nOR\nPress C/c to continue");
                System.out.print(">>> ");
                next = input.nextLine().charAt(0);

                if (next == 'S' | next == 's') {
                    Collections.shuffle(onDeckp2); // shuffle cards on deck
                    Collections.shuffle(onDeckp3); // shuffle cards on deck
                }
            } while (next == 'S' | next == 's');

            do {
                int sumP2 = 0, sumP3 = 0;
                int sumDiamondsP2 = 0, sumClubsP2 = 0, sumHeartsP2 = 0, sumSpadesP2 = 0;
                int sumDiamondsP3 = 0, sumClubsP3 = 0, sumHeartsP3 = 0, sumSpadesP3 = 0;
                int countDiamondsP2 = 0, countClubsP2 = 0, countHeartsP2 = 0, countSpadesP2 = 0;
                int countDiamondsP3 = 0, countClubsP3 = 0, countHeartsP3 = 0, countSpadesP3 = 0;
                System.out.println("\n*** ROUND " + nextRound + " ***");
                System.out.println("Cards at Hand:");

                // remove 5 cards from deck
                inHandp2 = removeCard(onDeckp2);
                inHandp3 = removeCard(onDeckp3);

                // Sort by face
                CardCompareFace cardCompareFace = new CardCompareFace();
                Collections.sort(inHandp2, cardCompareFace);
                Collections.sort(inHandp3, cardCompareFace);

                // Sort by suit
                CardCompareSuit cardCompareSuit = new CardCompareSuit();
                Collections.sort(inHandp2, cardCompareSuit);
                Collections.sort(inHandp3, cardCompareSuit);

                // Getting the total point for player 2
                for (int i = 0; i < inHandp2.size(); i++) {
                    if (inHandp2.get(i).getSuit() == 0) { // suit => "D"
                        countDiamondsP2++;
                        int tempD = inHandp2.get(i).getValue();
                        sumDiamondsP2 = sumDiamondsP2 + tempD;
                    } else if (inHandp2.get(i).getSuit() == 1) { // suit => "C"
                        countClubsP2++;
                        int tempC = inHandp2.get(i).getValue();
                        sumClubsP2 = sumClubsP2 + tempC;
                    } else if (inHandp2.get(i).getSuit() == 2) { // suit => "H"
                        countHeartsP2++;
                        int tempH = inHandp2.get(i).getValue();
                        sumHeartsP2 = sumHeartsP2 + tempH;
                    } else if (inHandp2.get(i).getSuit() == 3) { // suit => "S"
                        countSpadesP2++;
                        int tempS = inHandp2.get(i).getValue();
                        sumSpadesP2 = sumSpadesP2 + tempS;
                    }
                }
                // Getting the total point for player 3
                for (int i = 0; i < inHandp3.size(); i++) {
                    if (inHandp3.get(i).getSuit() == 0) { // suit => "D"
                        countDiamondsP3++;
                        int tempD = inHandp3.get(i).getValue();
                        sumDiamondsP3 = sumDiamondsP3 + tempD;
                    } else if (inHandp3.get(i).getSuit() == 1) { // suit => "C"
                        countClubsP3++;
                        int tempC = inHandp3.get(i).getValue();
                        sumClubsP3 = sumClubsP3 + tempC;
                    } else if (inHandp3.get(i).getSuit() == 2) { // suit => "H"
                        countHeartsP3++;
                        int tempH = inHandp3.get(i).getValue();
                        sumHeartsP3 = sumHeartsP3 + tempH;
                    } else if (inHandp3.get(i).getSuit() == 3) { // suit => "S"
                        countSpadesP3++;
                        int tempS = inHandp3.get(i).getValue();
                        sumSpadesP3 = sumSpadesP3 + tempS;
                    }
                }

                // to choose majority suits for sumP2
                if (countDiamondsP2 > countClubsP2 && countDiamondsP2 > countHeartsP2
                        && countDiamondsP2 > countSpadesP2)
                    sumP2 = sumP2 + sumDiamondsP2;
                else if (countClubsP2 > countDiamondsP2 && countClubsP2 > countHeartsP2 && countClubsP2 > countSpadesP2)
                    sumP2 = sumP2 + sumClubsP2;
                else if (countHeartsP2 > countDiamondsP2 && countHeartsP2 > countClubsP2
                        && countHeartsP2 > countSpadesP2)
                    sumP2 = sumP2 + sumHeartsP2;
                else if (countSpadesP2 > countDiamondsP2 && countSpadesP2 > countClubsP2
                        && countSpadesP2 > countHeartsP2)
                    sumP2 = sumP2 + sumSpadesP2;
                else if (countDiamondsP2 == countClubsP2 && countDiamondsP2 > countHeartsP2
                        && countDiamondsP2 > countSpadesP2 && countClubsP2 > countHeartsP2
                        && countClubsP2 > countSpadesP2) {
                    if (sumDiamondsP2 > sumClubsP2)
                        sumP2 = sumP2 + sumDiamondsP2;
                    else
                        sumP2 = sumP2 + sumClubsP2;
                } else if (countDiamondsP2 == countHeartsP2 && countDiamondsP2 > countClubsP2
                        && countDiamondsP2 > countSpadesP2 && countHeartsP2 > countClubsP2
                        && countHeartsP2 > countSpadesP2) {
                    if (sumDiamondsP2 > sumHeartsP2)
                        sumP2 = sumP2 + sumDiamondsP2;
                    else
                        sumP2 = sumP2 + sumHeartsP2;
                } else if (countDiamondsP2 == countSpadesP2 && countDiamondsP2 > countClubsP2
                        && countDiamondsP2 > countHeartsP2 && countSpadesP2 > countClubsP2
                        && countSpadesP2 > countHeartsP2) {
                    if (sumDiamondsP2 > sumSpadesP2)
                        sumP2 = sumP2 + sumDiamondsP2;
                    else
                        sumP2 = sumP2 + sumSpadesP2;
                } else if (countHeartsP2 == countClubsP2 && countHeartsP2 > countDiamondsP2
                        && countHeartsP2 > countSpadesP2 && countClubsP2 > countDiamondsP2
                        && countClubsP2 > countSpadesP2) {
                    if (sumHeartsP2 > sumClubsP2)
                        sumP2 = sumP2 + sumHeartsP2;
                    else
                        sumP2 = sumP2 + sumClubsP2;
                } else if (countHeartsP2 == countSpadesP2 && countHeartsP2 > countDiamondsP2
                        && countHeartsP2 > countClubsP2 && countSpadesP2 > countDiamondsP2
                        && countSpadesP2 > countClubsP2) {
                    if (sumHeartsP2 > sumSpadesP2)
                        sumP2 = sumP2 + sumHeartsP2;
                    else
                        sumP2 = sumP2 + sumSpadesP2;
                } else if (countClubsP2 == countSpadesP2 && countClubsP2 > countDiamondsP2
                        && countClubsP2 > countHeartsP2 && countSpadesP2 > countDiamondsP2
                        && countSpadesP2 > countHeartsP2) {
                    if (sumClubsP2 > sumSpadesP2)
                        sumP2 = sumP2 + sumClubsP2;
                    else
                        sumP2 = sumP2 + sumSpadesP2;
                }

                // to choose majority suits for sumP3
                if (countDiamondsP3 > countClubsP3 && countDiamondsP3 > countHeartsP3
                        && countDiamondsP3 > countSpadesP3)
                    sumP3 = sumP3 + sumDiamondsP3;
                else if (countClubsP3 > countDiamondsP3 && countClubsP3 > countHeartsP3 && countClubsP3 > countSpadesP3)
                    sumP3 = sumP3 + sumClubsP3;
                else if (countHeartsP3 > countDiamondsP3 && countHeartsP3 > countClubsP3
                        && countHeartsP3 > countSpadesP3)
                    sumP3 = sumP3 + sumHeartsP3;
                else if (countSpadesP3 > countDiamondsP3 && countSpadesP3 > countClubsP3
                        && countSpadesP3 > countHeartsP3)
                    sumP3 = sumP3 + sumSpadesP3;
                else if (countDiamondsP3 == countClubsP3 && countDiamondsP3 > countHeartsP3
                        && countDiamondsP3 > countSpadesP3 && countClubsP3 > countHeartsP3
                        && countClubsP3 > countSpadesP3) {
                    if (sumDiamondsP3 > sumClubsP3)
                        sumP3 = sumP3 + sumDiamondsP3;
                    else
                        sumP3 = sumP3 + sumClubsP3;
                } else if (countDiamondsP3 == countHeartsP3 && countDiamondsP3 > countClubsP3
                        && countDiamondsP3 > countSpadesP3 && countHeartsP3 > countClubsP3
                        && countHeartsP3 > countSpadesP3) {
                    if (sumDiamondsP3 > sumHeartsP3)
                        sumP3 = sumP3 + sumDiamondsP3;
                    else
                        sumP3 = sumP3 + sumHeartsP3;
                } else if (countDiamondsP3 == countSpadesP3 && countDiamondsP3 > countClubsP3
                        && countDiamondsP3 > countHeartsP3 && countSpadesP3 > countClubsP3
                        && countSpadesP3 > countHeartsP3) {
                    if (sumDiamondsP3 > sumSpadesP3)
                        sumP3 = sumP3 + sumDiamondsP3;
                    else
                        sumP3 = sumP3 + sumSpadesP3;
                } else if (countHeartsP3 == countClubsP3 && countHeartsP3 > countDiamondsP3
                        && countHeartsP3 > countSpadesP3 && countClubsP3 > countDiamondsP3
                        && countClubsP3 > countSpadesP3) {
                    if (sumHeartsP3 > sumClubsP3)
                        sumP3 = sumP3 + sumHeartsP3;
                    else
                        sumP3 = sumP3 + sumClubsP3;
                } else if (countHeartsP3 == countSpadesP3 && countHeartsP3 > countDiamondsP3
                        && countHeartsP3 > countClubsP3 && countSpadesP3 > countDiamondsP3
                        && countSpadesP3 > countClubsP3) {
                    if (sumHeartsP3 > sumSpadesP3)
                        sumP3 = sumP3 + sumHeartsP3;
                    else
                        sumP3 = sumP3 + sumSpadesP3;
                } else if (countClubsP3 == countSpadesP3 && countClubsP3 > countDiamondsP3
                        && countClubsP3 > countHeartsP3 && countSpadesP3 > countDiamondsP3
                        && countSpadesP3 > countHeartsP3) {
                    if (sumClubsP3 > sumSpadesP3)
                        sumP3 = sumP3 + sumClubsP3;
                    else
                        sumP3 = sumP3 + sumSpadesP3;
                }

                // display 5 cards in hand
                if (sumP2 > sumP3) {
                    System.out.println(playerTwo.getName() + "\t: "
                            + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP2 + "   | WIN");
                    System.out.println(playerThree.getName() + "\t: "
                            + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP3);
                } else if (sumP3 > sumP2) {
                    System.out.println(playerTwo.getName() + "\t: "
                            + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP2);
                    System.out.println(playerThree.getName() + "\t: "
                            + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP3 + "   | WIN");
                } else if (sumP2 == sumP3) {
                    System.out.println(playerTwo.getName() + "\t: "
                            + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP2 + "   | WIN");
                    System.out.println(playerThree.getName() + "\t: "
                            + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "") + "\t| Point = "
                            + sumP3 + "   | WIN");
                }

                // Choose the winner for the round
                if (sumP2 > sumP3)
                    pointP2 = pointP2 + sumP2;
                else if (sumP3 > sumP2)
                    pointP3 = pointP3 + sumP3;
                else if (sumP2 == sumP3) {
                    pointP2 = pointP2 + sumP2;
                    pointP3 = pointP3 + sumP3;
                }

                // print score for each player
                System.out.println("\nScore :");
                System.out.println(playerTwo.getName() + "\t: " + pointP2);
                System.out.println(playerThree.getName() + "\t: " + pointP3);

                // Remaining cards
                System.out.println("\nAvailable Cards:");
                System.out.println(playerTwo.getName() + "\t: "
                        + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));
                System.out.println(playerThree.getName() + "\t: "
                        + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));

                nextRound++;

                // condition to find the winner
                if (nextRound <= 4) {
                    System.out.println("\nPress ENTER to continue to 2-Player");
                    input.nextLine();
                } else {
                    if (pointP2 > pointP3)
                        System.out.println("\n**** " + playerTwo.getName() + " is the WINNER!! ****");
                    else if (pointP3 > pointP1)
                        System.out.println("\n**** " + playerThree.getName() + " is the WINNER!! ****");
                    // tie 1
                    else if (pointP2 == pointP3) {
                        do {
                            sumP2 = 0;
                            sumP3 = 0;

                            inHandp2 = removeOneCard(onDeckp2);
                            inHandp3 = removeOneCard(onDeckp3);

                            System.out.println("\n*** EXTRA ROUND ***");
                            int temp2 = inHandp1.get(0).getValue();
                            System.out.println("\nReveal 1 card from Player 2 : " + temp2);
                            sumP2 = sumP2 + temp2;

                            int temp3 = inHandp3.get(0).getValue();
                            System.out.println("Reveal 1 card from Player 3 : " + temp3 + "\n");
                            sumP3 = sumP3 + temp3;

                            if (sumP2 > sumP3) {
                                System.out.println(playerTwo.getName() + "\t: "
                                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP2 + "   | WIN");
                                System.out.println(playerThree.getName() + "\t: "
                                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP3);
                            } else {
                                System.out.println(playerTwo.getName() + "\t: "
                                        + inHandp2.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP2);
                                System.out.println(playerThree.getName() + "\t: "
                                        + inHandp3.toString().replace("[", "").replace("]", "").replace(",", "")
                                        + "\t| Point = " + sumP3 + "   | WIN");
                            }

                            pointP2 = pointP2 + sumP2; // add points to existing score
                            pointP3 = pointP3 + sumP3; // add points to existing score

                            System.out.println("\nScore :");
                            System.out.println(playerTwo.getName() + "\t: " + pointP2);
                            System.out.println(playerThree.getName() + "\t: " + pointP3);

                            System.out.println("\nAvailable Cards:");
                            System.out.println(playerTwo.getName() + "\t: "
                                    + onDeckp2.toString().replace("[", "").replace("]", "").replace(",", ""));
                            System.out.println(playerThree.getName() + "\t: "
                                    + onDeckp3.toString().replace("[", "").replace("]", "").replace(",", ""));

                            if (sumP2 > sumP3)
                                System.out.println("\n**** " + playerTwo.getName() + " is the WINNER!! ****");
                            else if (sumP3 > sumP2)
                                System.out.println("\n**** " + playerThree.getName() + " is the WINNER!! ****");
                        } while (pointP2 == pointP3);
                    }
                }
            } while (nextRound <= 4);
        }
    }

    public static void printPhase3() {
        System.out.println("\n******************");
        System.out.println("* 3-Player Phase *");
        System.out.println("******************\n");
    }

    public static void printPhase2() {
        System.out.println("******************");
        System.out.println("* 2-Player Phase *");
        System.out.println("******************");

    }
}