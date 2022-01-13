package actions;

import effects.Vulnerable;

public class AddVulnerable extends AbstractAction {
    public AddVulnerable(Target target, int amount) {
        super("AddVulnerable", target);

        switch (target) {
            case PLAYER -> self.addEffect(new Vulnerable(target, amount));
            case ENEMY -> enemy.addEffect(new Vulnerable(target, amount));
            case PLAYER_AND_ENEMY -> {
                self.addEffect(new Vulnerable(target, amount));
                enemy.addEffect(new Vulnerable(target, amount));
            }
            default -> System.out.println("AddBlock target not properly specified.");
        }
    }
}