package main.java.DOMParser.domain;

import java.util.List;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public class Player {

    private Action action;
    private double cash;
    private List<Card> cards;
    private Integer turn;

    public Player(List<Card> cards){
        this.cards = cards;
    };

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

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return "Player{" +
                "action=" + action +
                ", cash=" + cash +
                ", cards=" + cards +
                ", turn=" + turn +
                '}';
    }
}
