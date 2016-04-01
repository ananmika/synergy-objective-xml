package main.java.DOMParser.domain;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public enum ActionType implements SimpleObject{

    FOLD ("Fold"),
    CHECK ("Check"),
    CALL ("Call"),
    BET ("Bet"),
    RAISE ("Raise"),
    SHOW ("Show"),
    ALLIN ("AllIn");

    private final String name;

    ActionType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
