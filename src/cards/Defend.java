package cards;

import actions.AddBlock;

public class Defend extends AbstractCard {
    private static final String cardName = "Defend";

    public Defend() {
        super(cardName, CardType.SKILL, Target.PLAYER, 1, 0, 5);
    }

    @Override
    public void useCard() {
        new AddBlock(target, cardBlock);
    }

    @Override
    public String toString() {
        return super.toString() + "Gain " + cardBlock + " block.";
    }
}
