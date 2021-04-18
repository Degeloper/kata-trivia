package trivia;

import java.util.ArrayList;
import java.util.List;

class Players {
    private final List<String> players = new ArrayList<>();

    private final int[] purses = new int[6];

    private final Printer printer;

    private int currentPlayer = 0;

    Players(final Printer printer) {
        this.printer = printer;
    }

    void add(final String playerName) {
        players.add(playerName);
    }

    int size() {
        return players.size();
    }

    String currentPlayer() {
        return players.get(currentPlayer);
    }

    int currentPlayerId() {
        return currentPlayer;
    }

    void changePlayer() {
        currentPlayer++;
        if (currentPlayer == size()) currentPlayer = 0;
    }

    void incrementPurseOfCurrentPlayer() {
        purses[currentPlayerId()]++;
        printer.print(currentPlayer() + " now has " + purseOfCurrentPlayer() + " Gold Coins.");
    }

    private int purseOfCurrentPlayer() {
        return purses[currentPlayerId()];
    }

    boolean didPlayerWin() {
        return !(purseOfCurrentPlayer() == 6);
    }
}