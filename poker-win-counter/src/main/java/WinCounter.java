import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WinCounter {
    public static void main(String[] args) {
        WinCounter game = new WinCounter("src/main/resources/p054_poker.txt");
        try {
            game.countWins();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final String gameFile;
    private ArrayList<Player> players;
    private int numberOfPlayers = 2;

    public WinCounter(String gameFile) {
        this.gameFile = gameFile;
    }

    public void countWins() throws IOException {
        try (Scanner scanner = new Scanner(new File(gameFile))) {
            if (!scanner.hasNextLine()) {
                throw new NumberFormatException("No data in file.");
            }
            players = new ArrayList<>();
            for(int i = 0; i<numberOfPlayers; i++){
                Player player = new Player();
                players.add(player);
            }
            while (scanner.hasNextLine()) {
                readCards(scanner.nextLine(), numberOfPlayers);
                findWinner(numberOfPlayers).addWin();
                for (Player player: players) {
                    player.removeAllCards();
                }
            }
        }
        System.out.println("Player 1 won "+ players.get(0).getWins()+ " hands.");
        System.out.println("Player 2 won "+ players.get(1).getWins()+ " hands.");

    }

    private Player findWinner(int numberOfPlayers) {
        Player bestPlayer;
        int count = 0;
        bestPlayer = players.get(0);
        for (Player player: players) {
            player.countScoreForFlush();
            player.multiplyScoreForConsecutiveRanks();
            player.addScoreForMatchingRanks();
            if (player.getScore()==bestPlayer.getScore()) {
                count++;
            }
            if (player.getScore()>bestPlayer.getScore()) {
                bestPlayer = player;
                count = 1;
            }
        }
        while (count>1) {
            count = 0;
            for (Player player: players) {
                player.addScoreForHighestRank();
                if (player.getScore() == bestPlayer.getScore()) {
                    count++;
                }
                if (player.getScore()>bestPlayer.getScore()) {
                    bestPlayer = player;
                    count = 1;
                }
            }
        }
        return bestPlayer;
    }

    protected void readCards(String nextLine, int numberOfPlayers) {
        String cardName;
        try (Scanner scanner = new Scanner(nextLine)) {
            while (scanner.hasNext()) {
                for (int indexOfPlayer = 0; indexOfPlayer < numberOfPlayers; indexOfPlayer++) {
                    for (int i = 0; i < 5; i++) {
                        cardName = scanner.next();
                        switch (cardName.charAt(0)) {
                            case 'T':
                                players.get(indexOfPlayer).addCard(10, cardName.charAt(1));
                                break;
                            case 'J':
                                players.get(indexOfPlayer).addCard(11, cardName.charAt(1));
                                break;
                            case 'Q':
                                players.get(indexOfPlayer).addCard(12, cardName.charAt(1));
                                break;
                            case 'K':
                                players.get(indexOfPlayer).addCard(13, cardName.charAt(1));
                                break;
                            case 'A':
                                players.get(indexOfPlayer).addCard(14, cardName.charAt(1));
                                break;
                            default:
                                players.get(indexOfPlayer).addCard(cardName.charAt(0) - '0', cardName.charAt(1));
                        }
                    }
                    players.get(indexOfPlayer).sortCards();
                }
            }
        }
    }
}
