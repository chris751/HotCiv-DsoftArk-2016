package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

/**
 * Created by Christian on 15/11/2016.
 */
public class CityImpl implements City{
    private Position redCity = new Position(1,1);

    @Override
    public Player getOwner() {
        if(GameImpl.p.equals(redCity)){
            return Player.RED;
        }else {
            return Player.BLUE;
        }

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getProduction() {
        return null;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
