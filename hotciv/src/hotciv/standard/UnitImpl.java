package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Christian on 15/11/2016.
 */
public class UnitImpl implements Unit {

    private Player owner;
    private String type;

    public UnitImpl(Player owner, String type ) {
        this.owner = owner;
        this.type = type;
    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;

    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
