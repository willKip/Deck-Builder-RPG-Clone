package creatures;

import cards.AbstractCard;
import gamedata.SpireGame;

import java.util.ArrayList;

public class Player extends AbstractCreature {
    private int                             currentEnergy;
    private final int                       maxEnergy;
    private final ArrayList<AbstractCard>   playerDeck = new ArrayList<>();

    public Player(int initMaxHP, int initMaxEnergy) {
        super(initMaxHP);
        this.maxEnergy = initMaxEnergy;
        this.currentEnergy = initMaxEnergy;

        this.playerDeck.add(SpireGame.gameCardData.getCard("Pommel Strike"));
        this.playerDeck.add(SpireGame.gameCardData.getCard("Strike"));
        this.playerDeck.add(SpireGame.gameCardData.getCard("Strike"));
        this.playerDeck.add(SpireGame.gameCardData.getCard("Strike"));
        this.playerDeck.add(SpireGame.gameCardData.getCard("Strike"));
        this.playerDeck.add(SpireGame.gameCardData.getCard("Defend"));
        this.playerDeck.add(SpireGame.gameCardData.getCard("Defend"));
        this.playerDeck.add(SpireGame.gameCardData.getCard("Defend"));
        this.playerDeck.add(SpireGame.gameCardData.getCard("Defend"));
        this.playerDeck.add(SpireGame.gameCardData.getCard("Bash"));
    }

    public ArrayList<AbstractCard> getPlayerDeck() {
        return playerDeck;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void resetEnergy() {
        currentEnergy = maxEnergy;
    }

    public boolean canUseEnergy(int amount) {
        if (amount <= currentEnergy) {
            currentEnergy -= amount;
            return true;
        } else
            return false;
    }

    @Override
    public String toString() {
        return "| Player HP: " + getCurrentHP() + "/" + getMaxHP() + ", Block: " + getBlock()
                + ", Energy: <" + currentEnergy + "/" + maxEnergy + ">\n" + "| Effects: " + getEffects().toString();
    }
}
