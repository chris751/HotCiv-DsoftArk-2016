package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

/**
 * Created by Christian on 15/11/2016.
 */
public class UnitImpl implements Unit {
    Position archer = new Position(2,0);


    @Override
    public String getTypeString() {
        if(GameImpl.p.equals(archer)){
            return GameConstants.ARCHER;
        }else{
            return GameConstants.LEGION;
        }

    }

    @Override
    public Player getOwner() {
        if(GameImpl.p.equals(archer)){
            return Player.RED;
        }else{
            return Player.BLUE;
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
