package creatures;

import actions.AddRitual;
import actions.AddStrength;
import actions.DoDamage;

public class Cultist extends AbstractEnemy{
    private int                     turnIndex = 1;
    private static final String     enemyName = "Cultist";

    public Cultist(){
        super(enemyName, 50);
        //actions.add(new DoDamage(Target.SELF, 600));
    }

    @Override
    public void turnAction(){
        if(turnIndex == 1)
            new AddRitual(Target.ENEMY, 1);
        else{
            new AddStrength(Target.ENEMY, 1);
            new DoDamage(Target.PLAYER, 4 + turnIndex);
        }
        turnIndex++;
    }
}
