package actions;

public class AddBlock extends AbstractAction {
    public AddBlock(Target target, int amount) {
        super("AddBlock", target);

        switch (target) {
            case PLAYER -> self.addBlock(amount);
            case ENEMY -> enemy.addBlock(amount);
            case PLAYER_AND_ENEMY -> {
                self.addBlock(amount);
                enemy.addBlock(amount);
            }
            default -> System.out.println("AddBlock target not properly specified.");
        }
    }
}
