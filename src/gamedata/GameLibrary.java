package gamedata;

import cards.*;
import creatures.*;

import java.util.HashMap;

public class GameLibrary {
    private final HashMap<String, AbstractCard>     cards = new HashMap<>();
    private final HashMap<String, AbstractEnemy>    enemies = new HashMap<>();

    public AbstractCard getCard(String cardName) {
        return cards.get(cardName);
    }

    public AbstractEnemy getEnemy(String enemyName) {
        return enemies.get(enemyName);
    }

    public void addCard(AbstractCard c) {
        cards.put(c.getCardName(), c);  // cards will be accessed by their full, punctuated names
    }

    public void addEnemy(AbstractEnemy e) {
        enemies.put(e.getName(), e);
    }

    public void initializeCards() {
        addCard(new Strike());
        addCard(new Defend());
        addCard(new PommelStrike());
        addCard(new Bash());
    }

    public void initializeEnemies() {
        addEnemy(new Cultist());
    }
}
