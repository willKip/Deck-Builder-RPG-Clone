package creatures;

import actions.AbstractAction;
import gameinterfaces.TargetInterface;

import java.util.ArrayList;

public abstract class AbstractEnemy extends AbstractCreature implements TargetInterface {
    private final String name;
    protected ArrayList<AbstractAction> actions = new ArrayList<>();

    public AbstractEnemy(String initName, int initMaxHP) {
        super(initMaxHP);
        this.name = initName;
    }

    public String getName() {
        return name;
    }

    public void turnAction() {
    }

    @Override
    public String toString() {
        return "| " + name + " HP: " + getCurrentHP() + "/" + getMaxHP() + ", Block: " + getBlock() + "\n| Effects: " + getEffects().toString();
    }
}
