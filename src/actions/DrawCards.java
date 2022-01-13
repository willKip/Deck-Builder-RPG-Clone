package actions;

public class DrawCards extends AbstractAction {
    // draw always targets NONE

    public DrawCards(int drawCount) {
        super("DrawCards", Target.NONE);
        encounter.getCardPiles().drawCards(drawCount);
    }
}
