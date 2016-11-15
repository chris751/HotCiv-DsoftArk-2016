package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created by Christian on 15/11/2016.
 */
public class CityImpl implements City{
    @Override
    public Player getOwner() {
        return Player.RED;
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
