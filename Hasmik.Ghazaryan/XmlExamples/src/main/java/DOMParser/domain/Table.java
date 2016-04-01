package main.java.DOMParser.domain;

import java.util.List;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public class Table {

    private List<Player> players;

    public Table(){};

    public Table(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Table{" +
                "players=" + players +
                '}';
    }
}
