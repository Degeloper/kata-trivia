package trivia;

import java.util.ArrayList;
import java.util.List;

class Players {
    private final List<String> players = new ArrayList<>();

    private int currentPlayer = 0;

    Players() {
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
}