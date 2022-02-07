public class Card implements Comparable<Card> {

    int rank;
    char suit;

    public Card(int value, char suit) {
        this.rank = value;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }

    public int compareTo(Card card)
    {
        if (rank == card.rank)
            return 0;
        else if (rank > card.rank)
            return 1;
        else
            return -1;
    }
}
