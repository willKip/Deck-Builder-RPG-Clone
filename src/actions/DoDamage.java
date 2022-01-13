package actions;

public class DoDamage extends AbstractAction {
    private int selfDamage;
    private int enemyDamage;

    public DoDamage(Target target, int amount) {
        super("DoDamage", target);

        switch (target) {
            case PLAYER -> {
                selfDamage = amount;
                if (self.hasEffect("Strength")) {
                    System.out.println(self.getEffect("Strength").getMagicNumber());
                    selfDamage += self.getEffect("Strength").getMagicNumber();
                }

                if (self.hasEffect("Vulnerable"))
                    selfDamage = (int) Math.round(selfDamage * 1.5);

                if (self.getBlock() > 0) {
                    int damageDealt = selfDamage - self.getBlock();
                    if (damageDealt < 0)
                        damageDealt = 0;
                    self.setBlock(selfDamage - damageDealt);
                    selfDamage = damageDealt;
                }
            }
            case ENEMY -> {
                enemyDamage = amount;
                if (enemy.hasEffect("Strength"))
                    enemyDamage += enemy.getEffect("Strength").getMagicNumber();

                if (enemy.hasEffect("Vulnerable"))
                    enemyDamage = (int) Math.round(enemyDamage * 1.5);

                if (enemy.getBlock() > 0) {
                    int damageDealt = enemyDamage - enemy.getBlock();
                    if (damageDealt < 0)
                        damageDealt = 0;
                    enemy.setBlock(enemyDamage - damageDealt);
                    enemyDamage = damageDealt;
                }
            }
            case PLAYER_AND_ENEMY -> {
                selfDamage = amount;
                enemyDamage = amount;

                if (self.hasEffect("Strength"))
                    selfDamage += self.getEffect("Strength").getMagicNumber();
                if (enemy.hasEffect("Strength"))
                    enemyDamage += enemy.getEffect("Strength").getMagicNumber();

                if (self.hasEffect("Vulnerable"))
                    selfDamage = (int) Math.round(selfDamage * 1.5);
                if (enemy.hasEffect("Vulnerable"))
                    enemyDamage = (int) Math.round(enemyDamage * 1.5);

                if (self.getBlock() > 0) {
                    int damageDealt = selfDamage - self.getBlock();
                    if (damageDealt < 0)
                        damageDealt = 0;
                    self.setBlock(selfDamage - damageDealt);
                    selfDamage = damageDealt;
                }
                if (enemy.getBlock() > 0) {
                    int damageDealt = enemyDamage - enemy.getBlock();
                    if (damageDealt < 0)
                        damageDealt = 0;
                    enemy.setBlock(enemyDamage - damageDealt);
                    enemyDamage = damageDealt;
                }
            }
            default -> System.out.println("DoDamageAction target not properly specified.");
        }

        if (self.getBlock() < 0)
            self.resetBlock();
        if (enemy.getBlock() < 0)
            enemy.resetBlock();

        self.hpDamage(selfDamage);
        enemy.hpDamage(enemyDamage);
    }
}
