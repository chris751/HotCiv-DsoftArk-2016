package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.World;

/**
 * Created by Christian on 15/11/2016.
 */
public class WorldImpl implements World {
    static Position p;

    static Position archer = new Position(2,0);
    static Position legion = new Position(3,2);
    static Position settler = new Position(4,3);

    @Override
    public Unit removeUnitAt(Position from) {
        this.p = from;
        archer = null;
        return null;
    }

    @Override
    public void setUnitAt(Position to, Unit u) {
        archer = to;

    }

    @Override
    public Unit getUnitAt(Position p) {
        this.p = p;
        UnitImpl unitAtPosition = new UnitImpl();
        if(p.equals(archer)){
            return unitAtPosition;
        } else if(p.equals(legion)){
            return unitAtPosition;
        } else if(p.equals(settler)){
            return unitAtPosition;
        } else{
            return null;
        }
    }
}
