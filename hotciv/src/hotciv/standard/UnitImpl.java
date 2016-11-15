package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Christian on 15/11/2016.
 */
public class UnitImpl implements Unit {

    private Player owner;
    private Position p;
    private String type;

    public UnitImpl(Position p, Player owner, String type ) {
        this.p = p;
        this.owner = owner;
        this.type = type;
    }

    @Override
    public String getTypeString() {
        return type;
    }

    public Position getPosition() {
        return p;
    }

    @Override
    public Player getOwner() {
        return owner;

    }

    public void setPosition(Position p){
        this.p = p;
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
