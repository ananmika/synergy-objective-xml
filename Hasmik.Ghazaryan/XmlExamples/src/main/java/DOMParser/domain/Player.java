package main.java.DOMParser.domain;

import java.util.List;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public class Player {

    private Action action;
    private double cash;
    private List<Card> cards;

    public Player(List<Card> cards){};

    public Player(Action action, double cash, List<Card> cards) {
        this.action = action;
        this.cash = cash;
        this.cards = cards;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
