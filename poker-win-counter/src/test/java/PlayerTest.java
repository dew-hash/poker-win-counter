import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Player player;
    private WinCounter counter;

    @BeforeEach
    void setUp() {
        player = new Player();
        for (int i = 2; i < 7; i++) {
            player.addCard(i, 'C');
        }
        counter = new WinCounter("src/test/resources/p054_poker.txt");
    }

    @Test
    @DisplayName("Given cards of the same suit, should return 38428")
    void countScoreForFlush() {
        assertEquals(38436, player.countScoreForFlush());
    }

    @Test
    @DisplayName("Given cards of consecutive ranks, should return 38427")
    void multiplyScoreForConsecutiveRanks() {
        player.setScore(1);
        assertEquals(38427, player.multiplyScoreForConsecutiveRanks());
    }

    @Test
    @DisplayName("Given 2 cards of rank 8, should return 112")
    void addScoreForTwoMatchingRanks() {
        player.removeCard(0);
        player.removeCard(0);
        player.addCard(8, 'D');
        player.addCard(8, 'H');
        assertEquals(112, player.addScoreForMatchingRanks());
    }

    @Test
    @DisplayName("Given 4 cards of rank 14, should return 7529536")
    void addScoreForFourMatchingRanks() {
        player.removeCard(0);
        player.removeCard(0);
        player.removeCard(0);
        player.removeCard(0);
        player.addCard(14, 'D');
        player.addCard(14, 'H');
        player.addCard(14, 'S');
        player.addCard(14, 'C');
        assertEquals(7529536, player.addScoreForMatchingRanks());
    }

    @Test
    @DisplayName("Given 2 tens and 3 eights, should return 307468")
    void addScoreFor2And3MatchingRanks() {
        player.removeAllCards();
        player.addCard(10, 'H');
        player.addCard(10, 'D');
        player.addCard(8, 'D');
        player.addCard(8, 'H');
        player.addCard(8, 'C');
        assertEquals(307468, player.addScoreForMatchingRanks());
    }

    @Test
    @DisplayName("Given cards with highest rank 6, should return 6")
    void addScoreForHighestRank() {
        assertEquals(6, player.addScoreForHighestRank());

    }
}