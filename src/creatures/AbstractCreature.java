package creatures;

import effects.AbstractEffect;

import java.util.ArrayList;

public abstract class AbstractCreature {
    private int maxHP;
    private int currentHP;
    private int block = 0;
    private final ArrayList<AbstractEffect> effects = new ArrayList<>();

    public AbstractCreature(int initMaxHP) {
        this.maxHP = initMaxHP;
        this.currentHP = initMaxHP;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int newBlock) {
        block = newBlock;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int newHP) {
        currentHP = newHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int newMax) {
        maxHP = newMax;
    }

    public ArrayList<AbstractEffect> getEffects() {
        return effects;
    }

    public AbstractEffect getEffect(String effectName) {
        for (AbstractEffect e : effects) {
            if (e.getEffectName().equals(effectName))
                return e;
        }
        return null;
    }

    public boolean hasEffect(String effectName) {
        for (AbstractEffect e : effects) {
            if (e.getEffectName().equals(effectName))
                return true;
        }
        return false;
    }

    public void hpDamage(int amount) {
        currentHP -= amount;
    }

    public void addBlock(int amount) {
        block += amount;
    }

    public void addEffect(AbstractEffect effect) {
        if (hasEffect(effect.getEffectName())) {
            for (AbstractEffect e : effects) {
                if (e.getEffectName().equals(effect.getEffectName())) {
                    e.addDuration(effect.getDuration());
                    e.addMagicNumber(effect.getMagicNumber());
                }
            }
        } else {
            effects.add(effect);
        }
    }

    public boolean removeEffect(AbstractEffect effect) {
        return effects.remove(effect);
    }

    public void resetAllEffects() {
        effects.clear();
    }

    public void resetBlock() {
        block = 0;
    }
}
