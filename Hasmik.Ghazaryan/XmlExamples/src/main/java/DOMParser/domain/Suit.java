package main.java.DOMParser.domain;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public enum Suit implements SimpleObject{

    CLUBS ("Clubs"),
    DIAMONDS ("Diamonds"),
    HEARTS ("Hearts"),
    SPADES ("Spades");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
