package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameBetter implements IGame {

   private final Printer printer;

   private List<String> players = new ArrayList<>();
   private int[] places = new int[6];
   private int[] purses = new int[6];
   private boolean[] inPenaltyBox = new boolean[6];

   private LinkedList<String> popQuestions = new LinkedList<>();
   private LinkedList<String> scienceQuestions = new LinkedList<>();
   private LinkedList<String> sportsQuestions = new LinkedList<>();
   private LinkedList<String> rockQuestions = new LinkedList<>();

   private int currentPlayer = 0;
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
      printer.print(players.get(currentPlayer) + " is the current player");
      printer.print("They have rolled a " + roll);

      if (inPenaltyBox[currentPlayer]) if (roll % 2 != 0) {
         isGettingOutOfPenaltyBox = true;

         printer.print(players.get(currentPlayer) + " is getting out of the penalty box");
         places[currentPlayer] = places[currentPlayer] + roll;
         if (places[currentPlayer] > 11)
            places[currentPlayer] = places[currentPlayer] - 12;

         printer.print(players.get(currentPlayer)
                 + "'s new location is "
                 + places[currentPlayer]);
         printer.print("The category is " + currentCategory());
         askQuestion();
      } else {
         printer.print(players.get(currentPlayer) + " is not getting out of the penalty box");
         isGettingOutOfPenaltyBox = false;
      }
      else {

         places[currentPlayer] = places[currentPlayer] + roll;
         if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

         printer.print(players.get(currentPlayer)
                 + "'s new location is "
                 + places[currentPlayer]);
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
      if (places[currentPlayer] == 0) return "Pop";
      if (places[currentPlayer] == 4) return "Pop";
      if (places[currentPlayer] == 8) return "Pop";
      if (places[currentPlayer] == 1) return "Science";
      if (places[currentPlayer] == 5) return "Science";
      if (places[currentPlayer] == 9) return "Science";
      if (places[currentPlayer] == 2) return "Sports";
      if (places[currentPlayer] == 6) return "Sports";
      if (places[currentPlayer] == 10) return "Sports";
      return "Rock";
   }

   @Override
   public boolean wasCorrectlyAnswered() {
      if (inPenaltyBox[currentPlayer]) if (isGettingOutOfPenaltyBox) {
         printer.print("Answer was correct!!!!");
         purses[currentPlayer]++;
         printer.print(players.get(currentPlayer)
                 + " now has "
                 + purses[currentPlayer]
                 + " Gold Coins.");

         final boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      } else {
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;
         return true;
      }
      else {

         printer.print("Answer was correct!!!!");
         purses[currentPlayer]++;
         printer.print(players.get(currentPlayer)
                 + " now has "
                 + purses[currentPlayer]
                 + " Gold Coins.");

         final boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   @Override
   public boolean wrongAnswer() {
      printer.print("Question was incorrectly answered");
      printer.print(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
