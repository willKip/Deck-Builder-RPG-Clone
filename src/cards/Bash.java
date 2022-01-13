package cards;

import actions.AddVulnerable;
import actions.DoDamage;

public class Bash extends AbstractCard {
    private static final String cardName = "Bash";
    private static final int vulnDuration = 2;

    public Bash() {
        super(cardName, CardType.ATTACK, Target.ENEMY, 2, 8, 0);
    }

    @Override
    public void useCard() {
        new DoDamage(target, cardDamage);
        new AddVulnerable(target, vulnDuration);
    }

    @Override
    public String toString() {
        return super.toString() + "Deal " + cardDamage + " damage. Then apply " + vulnDuration + " vulnerable.";
    }
}

