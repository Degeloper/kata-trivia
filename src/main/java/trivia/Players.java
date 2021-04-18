package trivia;

import java.util.ArrayList;
import java.util.List;

class Players {
    private final List<Player> players = new ArrayList<>();

    private final Printer printer;

    private int currentPlayerId = 0;

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
        return players.get(currentPlayerId);
    }

    int currentPlayerId() {
        return currentPlayerId;
    }

    void changePlayer() {
        currentPlayerId++;
        if (currentPlayerId == size()) currentPlayerId = 0;
    }

    void currentPlayerAnsweredCorrect() {
        printer.print("Answer was correct!!!!");
        final int purseOfCurrentPlayer = currentPlayer().incrementPurse();
        printer.print(currentPlayer() + " now has " + purseOfCurrentPlayer + " Gold Coins.");
    }

    boolean didCurrentPlayerWin() {
        return currentPlayer().won();
    }

    int changePlaceOfCurrentPlayer(final int roll) {
        final int place = currentPlayer().changePlace(roll);
        printer.print(currentPlayer() + "'s new location is " + place);
        return place;
    }
}