package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Christian on 15/11/2016.
 */
public class WorldImpl implements World {
    static Position p;

    static Position archer = new Position(2,0);
    static Position legion = new Position(3,2);
    static Position settler = new Position(4,3);

    City redCity = new CityImpl(new Position(1,1), Player.RED);
    City blueCity = new CityImpl(new Position(4,1), Player.BLUE);

    Unit redArcher = new UnitImpl(new Position(2,0), Player.RED, GameConstants.ARCHER);
    Unit blueLegion = new UnitImpl(new Position(3,2), Player.BLUE, GameConstants.LEGION);
    Unit redSettler = new UnitImpl(new Position(4,3), Player.RED, GameConstants.SETTLER);

    @Override
    public Unit removeUnitAt(Position from) {
        this.p = from;
        return getUnitAt(from);
    }

    @Override
    public void setUnitAt(Position to, Unit u) {
        u.setPosition(to);

    }

    @Override
    public Unit getUnitAt(Position p) {
        this.p = p;
        if(p.equals(redArcher.getPosition())){
            return redArcher;
        }else if(p.equals(blueLegion.getPosition())){
            return blueLegion;
        }else if(p.equals(redSettler.getPosition())){
            return redSettler;
        }else {
            return null;
        }
    }

    public City getCityAt(Position p){
        if(p.equals(new Position(1,1))){
            return redCity;
        }else{
            return blueCity;
        }

    }
}
