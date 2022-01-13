package effects;

public class Vulnerable extends AbstractEffect{
    private static final String     name = "Vulnerable";

    public Vulnerable(Target target, int duration){
        super(name, target, duration, -1);
    }

    @Override
    public String toString() {
        return "Vulnerable: Target takes 50% more damage from attacks. " + super.toString();
    }
}
