package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Christian on 15/11/2016.
 */
public class UnitImpl implements Unit {

    private Player owner;
    private String type;
    private boolean isFortified = false;
    private int attackStrength;
    private int moveCount;
    private int defenseStength;

    public UnitImpl(Player owner, String type ) {
        this.owner = owner;
        this.type = type;

        if(type.equals(GameConstants.ARCHER)){
            defenseStength = 3;
            attackStrength = 2;
            moveCount = 1;
        }else if(type.equals(GameConstants.LEGION)){
            defenseStength = 2;
            attackStrength = 4;
            moveCount = 1;
        }else if(type.equals(GameConstants.SETTLER)){
            defenseStength = 3;
            attackStrength = 0;
            moveCount = 1;
        }
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
        return moveCount;
    }

    public void setFortified(){
        if(isFortified == false){
            isFortified = true;
        }else{
            isFortified = false;
        }
    }

    public boolean getFortified(){
        return isFortified;
    }

    @Override
    public int getDefensiveStrength() {
        if (isFortified) {
            return defenseStength * 2;
        } else {
            return defenseStength;
        }
    }

    @Override
    public int getAttackingStrength() {
        return attackStrength;
    }
}
