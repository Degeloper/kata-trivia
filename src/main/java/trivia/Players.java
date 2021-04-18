package trivia;

import java.util.ArrayList;
import java.util.List;

class Players {
    private final List<Player> players = new ArrayList<>();

    private final Printer printer;

    private int currentPlayer = 0;

    Players(final Printer printer) {
        this.printer = printer;
    }

    void add(final String playerName) {
        players.add(new Player(playerName));
    }

    int size() {
        return players.size();
    }

    Player currentPlayer() {
        return players.get(currentPlayer);
    }

    int currentPlayerId() {
        return currentPlayer;
    }

    void changePlayer() {
        currentPlayer++;
        if (currentPlayer == size()) currentPlayer = 0;
    }

    void currentPlayerAnsweredCorrect() {
        printer.print("Answer was correct!!!!");
        final int purseOfCurrentPlayer = currentPlayer().incrementPurse();
        printer.print(currentPlayer() + " now has " + purseOfCurrentPlayer + " Gold Coins.");
    }

    boolean didCurrentPlayerWin() {
        return currentPlayer().won();
    }
}