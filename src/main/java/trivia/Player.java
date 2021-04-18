package trivia;

public class Player {

    private final String name;

    private int purse = 0;

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
}
