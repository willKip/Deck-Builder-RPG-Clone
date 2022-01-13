package effects;

import gameinterfaces.TargetInterface;

public abstract class AbstractEffect implements TargetInterface{
    private final String        effectName;
    protected Target            target;
    private int                 duration;
    private int                 magicNumber;

    public String getEffectName() { return effectName; }

    public int getDuration() { return duration; }

    public int getMagicNumber() { return magicNumber; }

    public AbstractEffect(String effectName, Target target, int duration, int magicNumber){
        this.effectName = effectName;
        this.target = target;
        this.duration = duration;
        this.magicNumber = magicNumber;
    }

    public void addDuration(int amount){
        duration += amount;
    }
    public void addMagicNumber(int amount){ magicNumber += amount; }

    public boolean checkEffectDuration(){
        if(duration - 1 == 0){
            System.out.println("| Effect " + effectName + " wears off.");
            return true;
        }
        duration --;
        return false;
    }

    @Override
    public String toString() {
        String turnString = "";
        if(getDuration()>0)
            turnString = "(" + getDuration() + " turns left)";
        return turnString;
    }
}
