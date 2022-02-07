import java.util.ArrayList;
import java.util.Collections;

public class Player {

    int wins;
    ArrayList<Card> hand;
    int score;

    public Player() {
        this.wins = 0;
        this.score = 0;
        this.hand = new ArrayList<>();
    }

    public int getWins() {
        return wins;
    }

    public void addCard(int value, char suit) {
        this.hand.add(new Card(value, suit));
    }

    public void removeCard(int index) {
        this.hand.remove(index);
    }

    /**
     * Removes all cards from hand, makes hand an empty list
     */
    public void removeAllCards() {
        this.hand.removeAll(this.hand);
    }

    public void addWin() {
        this.wins++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sorts cards of hand so that they are in descending order
     */
    public void sortCards() {
        this.hand.sort(Collections.reverseOrder());
    }

    /**
     * Sets score to 38436 if hand contains any 5 cards of the same suit.
     * If hand doesn't contain 5 cards of the same suit, score is set to 1.
     * @return 38428 - if hand is flush, 1 - if hand is not flush.
     */
    public int countScoreForFlush() {
        for (int i = 0; i < this.hand.size()-1; i++) {
            if (!(hand.get(i).getSuit() == hand.get(i+1).getSuit())) {
                this.score = 1;
                return 1;
            }
        }
        this.score = 38436;
        return 38436;
    }

    /**
     * Multiplies score by 38427 if hand contains 5 cards of consecutive rank.
     * It doesn't matter if cards are from more than one suit.
     * @return 38427 - if hand is straight and 1 if it is not straight.
     */
    public int multiplyScoreForConsecutiveRanks() {
        for (int i = 1; i < this.hand.size(); i++) {
            if(this.hand.get(i-1).getRank()-this.hand.get(i).getRank()!=1) {
                return this.score;
            }
        }
        this.score = this.score * 38427;
        return this.score;
    }

    /**
     * Adds score for player's two, three or four cards of the same rank.
     * @returns the player's score after adding score for repetitive ranks.
     * If hand does not contain cards of the same rank
     * it adds to the player's score 0.
     */
    public int addScoreForMatchingRanks() {
        int [] counts = {1, 1, 1};
        int [] ranks = {0, 0, 0};
        int index=0;
        for (int i = 0; i < this.hand.size(); i++) {
            for (int j = i+1; j < this.hand.size(); j++) {
                if (this.hand.get(j).getRank() == this.hand.get(i).getRank()) {
                    ranks[index] = this.hand.get(i).getRank();
                    this.hand.remove(j);
                    j--;
                    counts[index]++;
                }
            }
            if (counts[index]>1) {
                index++;
                hand.remove(i);
                i--;
            }
        }
        if (counts[1]>counts[0]){
            int count = counts[0];
            int rank = ranks[0];
            counts[0] = counts[1];
            counts[1] = count;
            ranks[0] = ranks[1];
            ranks[1] = rank;
        }
        this.score = this.score + ((int) Math.pow(14,counts[0]-1+counts[0]/2+counts[1]-1)*ranks[0]+14*ranks[1]);
        return this.score;
    }

    /**
     * Adds score for player's highest rank card that is left in hand.
     * The first card in hand should be of highest rank because hand is
     * sorted in descending order, so the first card rank is added to
     * the player's score and the card is removed from hand.
     */
    public int addScoreForHighestRank() {
        this.score = this.score + this.hand.get(0).getRank();
        this.hand.remove(0);
        return this.score;
    }
}
