package trivia;

import java.util.LinkedList;

class Questions {

    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();
    private final Printer printer;

    Questions(final Printer printer) {
        this.printer = printer;
    }

    void init() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    void askQuestion(final int place) {
        if (categoryByPlace(place).equals("Pop"))
            printer.print(popQuestions.removeFirst());
        if (categoryByPlace(place).equals("Science"))
            printer.print(scienceQuestions.removeFirst());
        if (categoryByPlace(place).equals("Sports"))
            printer.print(sportsQuestions.removeFirst());
        if (categoryByPlace(place).equals("Rock"))
            printer.print(rockQuestions.removeFirst());
    }

    String categoryByPlace(final int place) {
        if (place == 0) return "Pop";
        if (place == 1) return "Science";
        if (place == 2) return "Sports";
        if (place == 3) return "Rock";
        if (place == 4) return "Pop";
        if (place == 5) return "Science";
        if (place == 6) return "Sports";
        if (place == 7) return "Rock";
        if (place == 8) return "Pop";
        if (place == 9) return "Science";
        if (place == 10) return "Sports";
        if (place == 11) return "Rock";
        throw new IllegalArgumentException("Place with id: [" + place + "] not valid");
    }
}