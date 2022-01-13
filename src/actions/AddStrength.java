package actions;

import effects.Strength;

public class AddStrength extends AbstractAction {
    public AddStrength(Target target, int amount) {
        super("AddStrength", target);

        switch (target) {
            case PLAYER -> self.addEffect(new Strength(target, amount));
            case ENEMY -> enemy.addEffect(new Strength(target, amount));
            case PLAYER_AND_ENEMY -> {
                self.addEffect(new Strength(target, amount));
                enemy.addEffect(new Strength(target, amount));
            }
            default -> System.out.println("AddStrength target not properly specified.");
        }
    }
}
