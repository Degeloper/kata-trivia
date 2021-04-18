package trivia;

public class GameBetter implements IGame {

   private final Printer printer;

   private final int[] places = new int[6];

   private final boolean[] inPenaltyBox = new boolean[6];

   private final Players players;

   private final Questions questions;

   private boolean isGettingOutOfPenaltyBox;

   public GameBetter(final Printer printer) {
      this.printer = printer;
      players = new Players(printer);
      questions = new Questions(printer);
      questions.init();
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   @Override
   public boolean add(final String playerName) {
      players.add(playerName);
      printer.print(playerName + " was added");
      printer.print("They are player number " + players.size());
      return true;
   }

   @Override
   public void roll(final int roll) {
      printer.print(players.currentPlayer() + " is the current player");
      printer.print("They have rolled a " + roll);

      if (inPenaltyBox[players.currentPlayerId()])
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            printer.print(players.currentPlayer() + " is getting out of the penalty box");
            rollAndAskQuestion(roll);
         } else {
            isGettingOutOfPenaltyBox = false;
            printer.print(players.currentPlayer() + " is not getting out of the penalty box");
         }
      else rollAndAskQuestion(roll);

   }

   private void rollAndAskQuestion(final int roll) {
      places[players.currentPlayerId()] = placeOfCurrentPlayer() + roll;
      if (placeOfCurrentPlayer() > 11)
         places[players.currentPlayerId()] = placeOfCurrentPlayer() - 12;
      printer.print(players.currentPlayer() + "'s new location is " + placeOfCurrentPlayer());
      printer.print("The category is " + questions.categoryByPlace(placeOfCurrentPlayer()));
      questions.askQuestion(placeOfCurrentPlayer());
   }

   @Override
   public boolean wasCorrectlyAnswered() {
      if (inPenaltyBox[players.currentPlayerId()])
         if (isGettingOutOfPenaltyBox) return correctAnswer();
         else {
            players.changePlayer();
            return true;
         }
      else return correctAnswer();
   }

   private boolean correctAnswer() {
      players.currentPlayerAnsweredCorrect();
      final boolean winner = players.didCurrentPlayerWin();
      players.changePlayer();
      return winner;
   }

   @Override
   public boolean wrongAnswer() {
      printer.print("Question was incorrectly answered");
      printer.print(players.currentPlayer() + " was sent to the penalty box");
      inPenaltyBox[players.currentPlayerId()] = true;
      players.changePlayer();
      return true;
   }

   private int howManyPlayers() {
      return players.size();
   }

   private int placeOfCurrentPlayer() {
      return places[players.currentPlayerId()];
   }


}
