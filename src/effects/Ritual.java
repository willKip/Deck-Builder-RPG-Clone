package effects;

import actions.AddStrength;

public class Ritual extends AbstractEffect{
    private static final String     name = "Ritual";
    private int                     ritualAmount;

    public Ritual(Target target, int amount){
        super(name, target, -1, amount);
        this.ritualAmount = getMagicNumber();
    }

    @Override
    public boolean checkEffectDuration() {
        new AddStrength(target, getMagicNumber());
        return super.checkEffectDuration();
    }

    @Override
    public String toString() {
        return "Ritual: Target gains " + ritualAmount + " Strength per turn" + super.toString();
    }
}