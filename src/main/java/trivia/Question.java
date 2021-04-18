package trivia;

class Question {

    private final Category category;
    private final String text;

    Question(final Category category, final String text) {
        this.category = category;
        this.text = text;
    }

    Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return text;
    }
}
