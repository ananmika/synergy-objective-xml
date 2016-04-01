package main.java.DOMParser.domain;

import java.math.BigDecimal;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public class Action {

    private ActionType actionType;
    private BigDecimal amount;

    public Action(ActionType actionType, BigDecimal amount) {
        this.actionType = actionType;
        this.amount = amount;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
