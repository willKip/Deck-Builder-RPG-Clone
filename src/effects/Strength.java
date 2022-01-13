package effects;

public class Strength extends AbstractEffect{
    private static final String     name = "Strength";
    private final int strengthAmount;

    public Strength(Target target, int amount){
        super(name, target, -1, amount);
        this.strengthAmount = getMagicNumber();
    }

    @Override
    public String toString() {
        return "Strength: Target does " + strengthAmount + " more damage for every attack" + super.toString();
    }
}
