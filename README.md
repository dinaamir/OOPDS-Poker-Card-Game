# OOPDS-Assignment-2---Poker-Card-Game

Card class    -> containing the information about the cards (suits, faces ,ranks, values)

Deck class    -> focuses on constructing a deck of playing cards
              -> method to create a card
              -> method to shuffle cards on deck
              -> method to deal/pop cards to deck
              -> method to remove 5 cards from deck
              
Player class  -> containing the information about player's name

Play class    -> main class, to play the game

** so far i have coded until the one where we need to get the values/points of each card to do totaling and comparison  

** the code might have problem when we wanna call the getValue() method sebab the data type does not match. cuba consider using generic <E> for inHand where its original data type was Stack<Card>