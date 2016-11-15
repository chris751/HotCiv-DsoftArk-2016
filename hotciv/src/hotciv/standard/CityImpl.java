package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

/**
 * Created by Christian on 15/11/2016.
 */
public class CityImpl implements City{
    private Position p;
    private String producing = GameConstants.SETTLER;
    private Player owner;

    public CityImpl(Position p, Player owner) {
        this.p = p;
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
