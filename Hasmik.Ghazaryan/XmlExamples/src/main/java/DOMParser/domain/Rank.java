package main.java.DOMParser.domain;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public enum Rank implements SimpleObject{

    TWO ("Two"),
    THREE ("Three"),
    FOUR ("Four"),
    FIVE ("Five"),
    SIX ("Six"),
    SEVEN ("Seven"),
    EIGHT ("Eignt"),
    NINE ("Nine"),
    TEN ("Ten"),
    JACK ("Jack"),
    QUEEN ("Queen"),
    KING ("King"),
    ACE ("Ace");

    private final String name;

    Rank(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
