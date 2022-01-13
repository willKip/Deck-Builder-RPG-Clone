package actions;

import effects.Ritual;

public class AddRitual extends AbstractAction {
    public AddRitual(Target target, int amount) {
        super("AddRitual", target);

        switch (target) {
            case PLAYER -> self.addEffect(new Ritual(target, amount));
            case ENEMY -> enemy.addEffect(new Ritual(target, amount));
            case PLAYER_AND_ENEMY -> {
                self.addEffect(new Ritual(target, amount));
                enemy.addEffect(new Ritual(target, amount));
            }
            default -> System.out.println("AddRitual target not properly specified.");
        }
    }
}
