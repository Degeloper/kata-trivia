package trivia;

import java.util.ArrayList;
import java.util.List;

import static trivia.Category.*;

class Questions {

    private final List<Question> questions = new ArrayList<>();

    private final Printer printer;

    Questions(final Printer printer) {
        this.printer = printer;
    }

    void init() {
        for (int i = 0; i < 50; i++) {
            questions.add(new Question(POP, "Pop Question " + i));
            questions.add(new Question(SCIENCE, "Science Question " + i));
            questions.add(new Question(SPORTS, "Sports Question " + i));
            questions.add(new Question(ROCK, "Rock Question " + i));
        }
    }

    void askQuestion(final int place) {
        final Category category = Category.categoryByPlace(place);
        printer.print("The category is " + category);
        final Question question = popQuestionByCategory(category);
        printer.print(question + "");

    }

    private Question popQuestionByCategory(final Category category) {
        final Question question = questions.stream()
                .filter(q -> q.getCategory() == category)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        questions.remove(question);
        return question;
    }

}