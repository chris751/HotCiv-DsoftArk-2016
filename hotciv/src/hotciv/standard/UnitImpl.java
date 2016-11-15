package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Christian on 15/11/2016.
 */
public class UnitImpl implements Unit {


    @Override
    public String getTypeString() {
        if(WorldImpl.p.equals(WorldImpl.archer)){
            return GameConstants.ARCHER;
        }else if(WorldImpl.p.equals(WorldImpl.legion)){
            return GameConstants.LEGION;
        }else if(WorldImpl.p.equals(WorldImpl.settler)){
            return GameConstants.SETTLER;
        } else{
            return null;
        }

    }

    @Override
    public Player getOwner() {
        if(WorldImpl.p.equals(WorldImpl.legion)){
            return Player.BLUE;
        }else{
            return Player.RED;
        }

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
