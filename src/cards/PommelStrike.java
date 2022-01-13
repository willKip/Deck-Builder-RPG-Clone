package cards;

import actions.DoDamage;
import actions.DrawCards;

public class PommelStrike extends AbstractCard {
    private static final String cardName = "Pommel Strike";
    private static final int drawCount = 1;

    public PommelStrike() {
        super(cardName, CardType.ATTACK, Target.ENEMY, 1, 9, 0);
    }

    @Override
    public void useCard() {
        new DoDamage(target, cardDamage);
        new DrawCards(drawCount);
    }

    @Override
    public String toString() {
        String pluralString = ".";
        if (drawCount > 1)
            pluralString = "s.";
        return super.toString() + "Deal " + cardDamage + " damage. Draw " + drawCount + " card" + pluralString;
    }
}
