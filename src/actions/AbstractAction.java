package actions;

import creatures.AbstractEnemy;
import creatures.Player;
import gamedata.Encounter;
import gamedata.SpireGame;
import gameinterfaces.TargetInterface;

public abstract class AbstractAction implements TargetInterface {
    protected String actionName;
    protected Encounter encounter;
    protected Player self;
    protected AbstractEnemy enemy;
    protected Target target;

    public AbstractAction(String actionName, Target target) {
        this.actionName = actionName;
        this.encounter = SpireGame.currentEncounter;
        this.self = encounter.getCurrentPlayer();
        this.enemy = encounter.getFloorEnemy();
        this.target = target;
    }
}
