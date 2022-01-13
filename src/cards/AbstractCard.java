package cards;

import gameinterfaces.TargetInterface;

public abstract class AbstractCard implements TargetInterface {
    protected Target target;
    protected String cardName;
    protected CardType cardType;
    protected int cardCost;
    protected int cardDamage;
    protected int cardBlock;

    public AbstractCard(String cardName, CardType cardType, Target cardTarget, int cardCost, int cardDamage, int cardBlock) {
        this.target = cardTarget;
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardCost = cardCost;
        this.cardDamage = cardDamage;
        this.cardBlock = cardBlock;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardCost() {
        return cardCost;
    }

    public abstract void useCard();

    @Override
    public String toString() {
        return cardName + ": <" + cardCost + "> ";
    }

    public enum CardType {
        ATTACK, SKILL, POWER
    }
}
