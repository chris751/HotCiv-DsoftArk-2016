package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
/**
 * Created by Christian on 15/11/2016.
 */
public class CityImpl implements City{
    private Player owner;
    public String unitProducing = GameConstants.SETTLER;

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
        return unitProducing;
    }

    public void changeProduction(String unitType){
        unitProducing = unitType;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
