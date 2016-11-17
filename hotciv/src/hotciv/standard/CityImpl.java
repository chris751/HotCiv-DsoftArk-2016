package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

/**
 * Created by Christian on 15/11/2016.
 */
public class CityImpl implements City{
    private String producing = GameConstants.SETTLER;
    private Player owner;

    public CityImpl(Player owner) {
        this.owner = owner;

    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getProduction() {

        return this.producing;
    }

    @Override
    public void setProduction(String unitType){
        this.producing = unitType;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
