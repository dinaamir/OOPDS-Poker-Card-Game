# OOPDS-Assignment-2---Poker-Card-Game

+---------------------------+
|    Class Clarification    |
+---------------------------+

1. Card class    -> containing the information about the cards (suits, faces ,ranks, values)

2. Deck class    -> focuses on constructing a deck of playing cards
              -> method to create a card
              -> method to shuffle cards on deck
              -> method to deal/pop cards to deck
              -> method to remove 5 cards from deck
              -> method to remove 1 card from deck
              -> method to compare cards by suit then face

3. Player class  -> containing the information about player's name

4. Game class    -> main class, to play the game

+--------------------------+
|    Majority Rule Game    |
+--------------------------+

RULES
1. The game will start in 3-Player Phase then move on to 2-Player Phase.
2. All 52 cards will be distributed to each player at the beginning of each phase.
3. Players are allowed to shuffle their cards as many time as they wish at the beginning of each phase.
4. Showdown round, players are each dealt 5 cards.
5. Cards chosen from the suit that gives the highest total will calculate as the point.
6. Player with the highest score wins the game.
