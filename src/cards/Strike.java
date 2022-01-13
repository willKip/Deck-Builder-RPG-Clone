package cards;

import actions.DoDamage;

public class Strike extends AbstractCard {
    private static final String cardName = "Strike";

    public Strike() {
        super(cardName, CardType.ATTACK, Target.ENEMY, 1, 6, 0);
    }

    @Override
    public void useCard() {
        new DoDamage(target, cardDamage);
    }

    @Override
    public String toString() {
        return super.toString() + "Deal " + cardDamage + " damage.";
    }
}
