package trivia;

public class Player {

    private final String name;

    private int purse = 0;

    private int place = 0;

    Player(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    int incrementPurse() {
        return ++purse;
    }

    boolean won() {
        return !(purse == 6);
    }

    int changePlace(final int roll) {
        place = place + roll;
        if (place > 11)
            place = place - 12;
        return place;
    }
}
