package trivia;

import static java.util.Arrays.stream;

public enum Category {

    POP(1, "Pop"),
    SCIENCE(2, "Science"),
    SPORTS(3, "Sports"),
    ROCK(4, "Rock");

    private final int id;
    private final String name;

    Category(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    static Category categoryByPlace(final int place) {
        return stream(values())
                .filter(c -> place % numberOfCategories() == c.id - 1)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static int numberOfCategories() {
        return values().length;
    }

    @Override
    public String toString() {
        return name;
    }
}
