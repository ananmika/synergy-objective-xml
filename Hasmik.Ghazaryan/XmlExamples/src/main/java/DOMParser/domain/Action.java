package main.java.DOMParser.domain;

import com.sun.istack.internal.Nullable;

import java.math.BigDecimal;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public class Action {

    private String actionType;
    private double amount;

    public Action(String actionType) {
        this.actionType = actionType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionType='" + actionType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
