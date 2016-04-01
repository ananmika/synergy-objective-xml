package main.java.DOMParser.domain;

import java.util.List;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public class Table {

    private List<Player> players;
    private List<Card> cards;

    public Table(){};

    public Table(List<Player> players, List<Card> cards) {
        this.players = players;
        this.cards = cards;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
