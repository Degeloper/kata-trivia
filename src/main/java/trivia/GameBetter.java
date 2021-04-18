package trivia;

import java.util.LinkedList;

public class GameBetter implements IGame {

   private final Printer printer;

   private final int[] places = new int[6];
   private final int[] purses = new int[6];
   private final boolean[] inPenaltyBox = new boolean[6];

   private final LinkedList<String> popQuestions = new LinkedList<>();
   private final LinkedList<String> scienceQuestions = new LinkedList<>();
   private final LinkedList<String> sportsQuestions = new LinkedList<>();
   private final LinkedList<String> rockQuestions = new LinkedList<>();
   private final Players players = new Players();

   private boolean isGettingOutOfPenaltyBox;

   public GameBetter(final Printer printer) {
      this.printer = printer;
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast(("Science Question " + i));
         sportsQuestions.addLast(("Sports Question " + i));
         rockQuestions.addLast(createRockQuestion(i));
      }
   }

   private String createRockQuestion(final int index) {
      return "Rock Question " + index;
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   @Override
   public boolean add(final String playerName) {
      players.add(playerName);
      places[howManyPlayers()] = 0;
      purses[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;

      printer.print(playerName + " was added");
      printer.print("They are player number " + players.size());
      return true;
   }

   private int howManyPlayers() {
      return players.size();
   }

   @Override
   public void roll(final int roll) {
      printer.print(players.currentPlayer() + " is the current player");
      printer.print("They have rolled a " + roll);

      if (inPenaltyBox[players.currentPlayerId()]) if (roll % 2 != 0) {
         isGettingOutOfPenaltyBox = true;

         printer.print(players.currentPlayer() + " is getting out of the penalty box");
         places[players.currentPlayerId()] = places[players.currentPlayerId()] + roll;
         if (places[players.currentPlayerId()] > 11)
            places[players.currentPlayerId()] = places[players.currentPlayerId()] - 12;

         printer.print(players.currentPlayer()
                 + "'s new location is "
                 + places[players.currentPlayerId()]);
         printer.print("The category is " + currentCategory());
         askQuestion();
      } else {
         printer.print(players.currentPlayer() + " is not getting out of the penalty box");
         isGettingOutOfPenaltyBox = false;
      }
      else {
         places[players.currentPlayerId()] = places[players.currentPlayerId()] + roll;
         if (places[players.currentPlayerId()] > 11)
            places[players.currentPlayerId()] = places[players.currentPlayerId()] - 12;

         printer.print(players.currentPlayer()
                 + "'s new location is "
                 + places[players.currentPlayerId()]);
         printer.print("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      if (currentCategory().equals("Pop"))
         printer.print(popQuestions.removeFirst());
      if (currentCategory().equals("Science"))
         printer.print(scienceQuestions.removeFirst());
      if (currentCategory().equals("Sports"))
         printer.print(sportsQuestions.removeFirst());
      if (currentCategory().equals("Rock"))
         printer.print(rockQuestions.removeFirst());
   }

   private String currentCategory() {
      if (places[players.currentPlayerId()] == 0) return "Pop";
      if (places[players.currentPlayerId()] == 4) return "Pop";
      if (places[players.currentPlayerId()] == 8) return "Pop";
      if (places[players.currentPlayerId()] == 1) return "Science";
      if (places[players.currentPlayerId()] == 5) return "Science";
      if (places[players.currentPlayerId()] == 9) return "Science";
      if (places[players.currentPlayerId()] == 2) return "Sports";
      if (places[players.currentPlayerId()] == 6) return "Sports";
      if (places[players.currentPlayerId()] == 10) return "Sports";
      return "Rock";
   }

   @Override
   public boolean wasCorrectlyAnswered() {
      if (inPenaltyBox[players.currentPlayerId()]) if (isGettingOutOfPenaltyBox) {
         printer.print("Answer was correct!!!!");
         purses[players.currentPlayerId()]++;
         printer.print(players.currentPlayer()
                 + " now has "
                 + purses[players.currentPlayerId()]
                 + " Gold Coins.");

         final boolean winner = didPlayerWin();
         players.changePlayer();
         return winner;
      } else {
         players.changePlayer();
         return true;
      }
      else {
         printer.print("Answer was correct!!!!");
         purses[players.currentPlayerId()]++;
         printer.print(players.currentPlayer()
                 + " now has "
                 + purses[players.currentPlayerId()]
                 + " Gold Coins.");

         final boolean winner = didPlayerWin();
         players.changePlayer();

         return winner;
      }
   }

   @Override
   public boolean wrongAnswer() {
      printer.print("Question was incorrectly answered");
      printer.print(players.currentPlayer() + " was sent to the penalty box");
      inPenaltyBox[players.currentPlayerId()] = true;
      players.changePlayer();
      return true;
   }

   private boolean didPlayerWin() {
      return !(purses[players.currentPlayerId()] == 6);
   }
}
