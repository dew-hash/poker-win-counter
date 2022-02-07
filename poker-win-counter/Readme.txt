This poker-win-counter is written by Rasa Petrauskiene.

This poker-win-counter counts the score for hand in such manner:
First it gives 38436 points for player, if all his cards in hand
are of the same suit. It they are not, the player gets 1 point.
Then the counter checks if all player's cards are of consecutive ranks.
If they are, the player's score is multiplied by 38427. If the cards
are not of consecutive ranks, the score is not multiplied. Then the
score for repetitive rank cards is counted and added to the player's
score. The score for a pair of the same rank is counted like this:
14*x, where x is the rank of the pair. The score for two pairs is
14*14*x+14*y, where x i rank of higher rank cards pair and y is the
rank of lower rank cards pair. The score three cards of the same rank is
14*14*14*x, where x is the rank of repetitive rank cards. Three cards of the
same rank and a pair of other rank cards is 14*14*14*14*x+14*y where x is
the rank of 3 repetitive rank cards and y is the rank of the cards of the
pair. The core for 4 cards of the same rank is 14*14*14*14*14*x, where x
is the rank of four cards in hand. When the repetitive rank cards are found
they are removed from hand.
The rank for cards is as follows:
2 is 2, 3 is 3, 4 is 4, 5 is 5, 6 is 6, 7 is 7, 8 is 8, 9 is 9, T is 10,
J is 11, Q is 12, K is 13, A is 14.
If the winner is not clear after counting the scores for repetitive ranks,
then the counter looks for highest rank card in the remaining cards in hand.
It removes the card of highest rank and adds the value of that rank to the
player's score. This process of taking highest rank card is repeated
until the winner is clear.
The maximum score the player can get for the hand, so called Royal flush,
is 1476980186 which is achieved if the player's all cards in hand are of the
same suit, consecutive ranks and contains Ace.


