package gamedata;

import cards.AbstractCard;

import java.util.ArrayList;
import java.util.Collections;

public class CardPiles {
    private static final int HAND_CARD_MAX = 10;

    protected ArrayList<AbstractCard> drawPile;
    protected ArrayList<AbstractCard> discardPile;
    protected ArrayList<AbstractCard> handPile;

    public CardPiles(ArrayList<AbstractCard> deck) {
        this.drawPile = new ArrayList<>(deck);   // load in deck cards to draw pile
        this.discardPile = new ArrayList<>();
        this.handPile = new ArrayList<>();

        shufflePile(drawPile);
    }

    public ArrayList<AbstractCard> getHandPile() {
        return handPile;
    }

    public void shufflePile(ArrayList<AbstractCard> pile) {
        Collections.shuffle(pile);
    }

    public void addCard(ArrayList<AbstractCard> p, AbstractCard c) {
        p.add(c);
    }

    public boolean removeCard(ArrayList<AbstractCard> p, AbstractCard c) {
        return p.remove(c);
    }

    // using a card means a hand pile card is sent to the discard pile
    public void cardUsed(AbstractCard c) {
        handPile.remove(c);
        discardPile.add(c);
    }

    public void startTurn() {
        // later introduce a variable to alter number of cards drawn per turn
        drawCards(5);
    }

    public void endTurn() {
        // at end of turn, all hand cards are sent to the discard pile
        moveAllCards(handPile, discardPile);
    }

    public void drawCards(int count) {
        // if not enough cards were in the draw pile, refresh and try again (only once)
        int leftoverCards = moveCards(count, drawPile, handPile);
        if (leftoverCards > 0) {
            refreshDrawPile();
            moveCards(leftoverCards, drawPile, handPile);
        }

        // send cards over hand max card cap to discard pile
        while (handPile.size() > HAND_CARD_MAX) {
            handPile.remove(handPile.size() - 1);
            moveCards(handPile.size() - HAND_CARD_MAX, handPile, discardPile);
        }
    }

    public void refreshDrawPile() {
        moveAllCards(discardPile, drawPile);
        shufflePile(drawPile);
    }

    // returns integer of cards not moved (due to pile being empty). movement targets last card in pile
    public int moveCards(int count, ArrayList<AbstractCard> source, ArrayList<AbstractCard> target) {
        while (source.size() > 0 && count > 0) {
            target.add(source.get(source.size() - 1));
            source.remove(source.size() - 1);
            count--;
        }
        return count;
    }

    public void moveAllCards(ArrayList<AbstractCard> source, ArrayList<AbstractCard> target) {
        moveCards(source.size(), source, target);
    }

    public void pilePrint(ArrayList<AbstractCard> pile, boolean randomOrder) {
        ArrayList<AbstractCard> pileCopy = new ArrayList<>(pile);
        if (randomOrder)
            Collections.shuffle(pileCopy);

        if (pileCopy.size() == 0)
            System.out.println("    | No cards in the pile!");
        else {
            for (AbstractCard c : pileCopy) {
                System.out.println("    | " + c);
            }
        }
    }
}
